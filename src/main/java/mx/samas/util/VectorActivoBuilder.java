/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import mx.samas.domain.VectorActivo;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class VectorActivoBuilder {

    private final HashMap<String, VectorActivo> vectorActivos;
    private final Date fecha;

    public VectorActivoBuilder(Date fecha) throws IOException {
        this.vectorActivos = new HashMap<>();
        this.fecha = fecha;
    }

    public void addVectorActivo(CSVRecord record) {
        VectorActivo va = new VectorActivo();
        va.setPrecioLimpio(clean(record.get(5)));
        va.setFecha(fecha);
        vectorActivos.put(generateClavePizarra(record.get(1), record.get(2),
                record.get(3)), va);
    }

    private Double clean(String i) {
        try {
            return Double.valueOf(i);
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    private String generateClavePizarra(String tv, String emi, String serie) {
        String cp = tv + "_" + emi + "_" + serie;
        return cp.replace("\"", "").replace("\'", "").trim();
    }

    public HashMap<String, VectorActivo> getVectorActivos() {
        return vectorActivos;
    }
}
