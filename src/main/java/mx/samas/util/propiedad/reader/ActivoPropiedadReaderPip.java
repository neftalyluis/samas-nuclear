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
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class ActivoPropiedadReaderPip extends ActivoPropiedadReader {

    private static final Logger LOG = Logger.getLogger(ActivoPropiedadReaderPip.class.getName());
    private String urlFile;
    private static final String PIP_LOCATION = "vector/pip/";
    private static final char PIP_DELIMITER = '\'';

    public ActivoPropiedadReaderPip(String clavePizarra) {
        super(clavePizarra);
    }

    @Override
    public void execute() throws FileNotFoundException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Reader in = new FileReader(classLoader.getResource(PIP_LOCATION + urlFile).getFile());
        final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader().withQuote(PIP_DELIMITER));
        for (CSVRecord record : parser) {
//            activoBuilder.addActivo(record);
//            vectorActivoBuilder.addVectorActivo(record);
        }
    }

}
