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
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;
import mx.samas.domain.Activo;
import mx.samas.domain.VectorActivo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class VectorPipParser {

    private static final Logger LOG = Logger.getLogger(VectorPipParser.class.getName());

    private final String urlFile;

    private static final String PIP_LOCATION = "vector/pip/";

    private static final char PIP_DELIMITER = '\'';

    private final ActivoBuilder activoBuilder;

    private final VectorActivoBuilder vectorActivoBuilder;

    public VectorPipParser(String url, Date fecha) throws IOException {
        this.urlFile = url;
        this.activoBuilder = new ActivoBuilder();
        this.vectorActivoBuilder = new VectorActivoBuilder(fecha);
    }

    public void execute() throws FileNotFoundException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        Reader in = new FileReader(classLoader.getResource(PIP_LOCATION + urlFile).getFile());
        final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader().withQuote(PIP_DELIMITER));
        for (CSVRecord record : parser) {
            activoBuilder.addActivo(record);
            vectorActivoBuilder.addVectorActivo(record);
        }
    }

    public HashMap<String, Activo> getActivoMap() {
        return activoBuilder.getActivos();
    }

    public HashMap<String, VectorActivo> getVectorActivoMap() {
        return vectorActivoBuilder.getVectorActivos();
    }
}
