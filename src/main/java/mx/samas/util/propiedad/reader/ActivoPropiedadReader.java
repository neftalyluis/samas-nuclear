/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util.propiedad.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.ActivoPropiedadValor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class ActivoPropiedadReader {

    private String clavePizarra;
    private List<ActivoPropiedad> indexList = new ArrayList<>();
    private HashMap<ActivoPropiedad, ActivoPropiedadValor> propiedadMap = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(ActivoPropiedadReader.class.getName());
    private String urlFile;
    private String LOCATION = "vector/pip/";
    private char QUOTE = '\'';
    private char DELIMITER = ',';

    public ActivoPropiedadReader(String claveP, String nombreArchivo, List<ActivoPropiedad> list) {
        this.clavePizarra = claveP;
        this.urlFile = nombreArchivo;
        this.indexList.addAll(list);
    }

    public ActivoPropiedadReader(String claveP, String nombreArchivo, List<ActivoPropiedad> list, char quote) {
        this.clavePizarra = claveP;
        this.urlFile = nombreArchivo;
        this.indexList.addAll(list);
        this.QUOTE = quote;
    }

    public ActivoPropiedadValor getPropiedadValor(ActivoPropiedad indice) {
        return propiedadMap.get(indice);
    }

    public void execute() throws FileNotFoundException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Reader in = new FileReader(classLoader.getResource(LOCATION + urlFile).getFile());
        final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader().withQuote(QUOTE).withDelimiter(DELIMITER));
        for (CSVRecord record : parser) {
            if (sameClavePizarra(record, clavePizarra)) {
                for (ActivoPropiedad ap : indexList) {
                    propiedadMap.put(ap, new ActivoPropiedadValor(
                            record.get(ap.getIndice()), ap));
                }
            }
        }
    }

    private Boolean sameClavePizarra(CSVRecord record, String cpToCompare) {
        String cp = generateClavePizarra(record.get(1), record.get(2), record.get(3));
        return cpToCompare.equals(cp);
    }

    private String generateClavePizarra(String tv, String emi, String serie) {
        String cp = tv + "_" + emi + "_" + serie;
        return cp.replace("\"", "").replace("\'", "").trim();
    }

    public Collection<ActivoPropiedadValor> getPropiedadesValor() {
        return propiedadMap.values();
    }

}
