/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.FuenteDatos;
import mx.samas.elastic.domain.VectorActivoPropiedadValor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.Resource;

/**
 * @author samas
 */
public class VectorPropiedadesItemProcessor implements ItemProcessor<Activo, VectorActivoPropiedadValor> {

    private static final Logger LOG = Logger.getLogger(VectorPropiedadesItemProcessor.class.getName());

    private final Date fecha;

    private final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    private final Resource locationFile;

    public VectorPropiedadesItemProcessor(Date fecha, Resource locationFile) {
        this.fecha = fecha;
        this.locationFile = locationFile;
    }

    @Override
    public VectorActivoPropiedadValor process(Activo item) throws Exception {

        List<ActivoPropiedad> propiedadVectorialPip = item.getPropiedades()
                .stream()
                .filter(ap -> ap.getOrigenDatos() == FuenteDatos.VECTOR_PIP
                        && ap.getVectorial() == true)
                .collect(Collectors.toList());

        String clavePizarraVectorial = item.getClavePizarra() + formatter.format(fecha);

        List<String> recordCSV = getForActivo(item.getClavePizarra());

        Map<String, Object> mapValores = generateProperties(recordCSV, propiedadVectorialPip);

        return new VectorActivoPropiedadValor(clavePizarraVectorial, mapValores);
    }

    private Map<String, Object> generateProperties(List<String> record,
            List<ActivoPropiedad> propiedades) {

        HashMap<String, Object> valores = new HashMap<>();

        //TODO: Hacer validacion de tipos
        for (ActivoPropiedad prop : propiedades) {
            Integer index = prop.getIndice();
            valores.put(prop.getNombre(), record.get(index));
        }

        return valores;
    }

    private List<String> getForActivo(String clavePizarra) {

        List<String> recordList = new ArrayList<>();

        try (Reader in = new InputStreamReader(locationFile.getInputStream())) {
            final CSVParser parser = new CSVParser(in,
                    CSVFormat.EXCEL
                    .withHeader()
                    .withQuote('\'')
                    .withDelimiter(','));

            for (CSVRecord record : parser) {
                if (sameClavePizarra(record, clavePizarra) && record.isConsistent()) {
                    record.iterator().forEachRemaining(recordList::add);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(VectorPropiedadesItemProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recordList;
    }

    private Boolean sameClavePizarra(CSVRecord record, String cpToCompare) {
        String cp = generateClavePizarra(record.get(1), record.get(2), record.get(3));
        return cpToCompare.equals(cp);
    }

    private String generateClavePizarra(String tv, String emi, String serie) {
        String cp = tv + "_" + emi + "_" + serie;
        return cp.replace("\"", "").replace("\'", "").trim();
    }

}
