/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util;

import java.io.IOException;
import java.util.HashMap;
import mx.samas.domain.Activo;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author samas
 */
public class ActivoBuilder {

    private final HashMap<String, Activo> activos;
    private final TipoActivoResolver tipoActivoResolver;

    public ActivoBuilder() throws IOException {
        this.activos = new HashMap<>();
        this.tipoActivoResolver = new TipoActivoResolver();
    }

    public void addActivo(CSVRecord record) {

        Activo a = new Activo(clean(record.get(1)), clean(record.get(2)),
                clean(record.get(3)), null, clean(record.get(9)),
                tipoActivoResolver.resolveFromTipoValor(clean(record.get(1))),
                Boolean.FALSE, null);

        activos.put(a.getClavePizarra(), a);
    }

    private String clean(String i) {
        return i.replace("\"", "").replace("\'", "").trim();
    }

    public HashMap<String, Activo> getActivos() {
        return activos;
    }

}
