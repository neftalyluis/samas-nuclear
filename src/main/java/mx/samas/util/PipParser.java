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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class PipParser {

    private static final Logger LOG = Logger.getLogger(PipParser.class.getName());

    private final String urlFile;

    private static final String PIP_LOCATION = "vector/pip/";

    private static final char PIP_DELIMITER = '\'';

    public PipParser(String url) {
        this.urlFile = url;
        LOG.log(Level.INFO, "Inicializamos PipReader para el archivo: {0}", url);
    }

    public void printNombres() throws FileNotFoundException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        Reader in = new FileReader(classLoader.getResource(PIP_LOCATION + urlFile).getFile());
        final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader().withQuote(PIP_DELIMITER));
        LOG.info("Imprimimos nombres de activos en vector para debug");
        for (CSVRecord record : parser) {
            LOG.info(record.get(9));
        }
    }
}
