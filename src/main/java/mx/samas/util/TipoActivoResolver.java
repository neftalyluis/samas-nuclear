/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.TipoActivo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class TipoActivoResolver {
    
    private static final Logger LOG = Logger.getLogger(TipoActivoResolver.class.getName());
    
    private HashMap<String, TipoActivo> resolver;
    
    private static final String ROUTE = "vector/pip/TipoActivo.csv";
    
    public TipoActivoResolver() throws FileNotFoundException, IOException {
        resolver = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        Reader in = new FileReader(classLoader.getResource(ROUTE).getFile());
        final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL);
        for (CSVRecord record : parser) {
            resolver.put(record.get(1).trim(), resolveFromCSV(record.get(0)));
        }
    }
    
    public TipoActivo resolveFromTipoValor(String tipo) {
        TipoActivo tip = resolver.get(tipo);
        if (tip == null) {
            LOG.log(Level.INFO, "El TipoValor {0} no esta definido en el CSV/DB, procedemos a asignarlo como Accion", tipo);
            return TipoActivo.ACCION;
        } else {
            return tip;
        }
    }
    
    private TipoActivo resolveFromCSV(String entry) {
        switch (entry) {
            case "Equity":
                return TipoActivo.ACCION;
            case "Bond":
                return TipoActivo.BONO;
            case "Asset":
                return TipoActivo.DERIVADO;
            case "Currency":
                return TipoActivo.MONEDA;
            default:
                return TipoActivo.ACCION;
        }
    }
    
}
