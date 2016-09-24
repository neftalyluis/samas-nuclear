/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util.propiedad.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.ActivoPropiedadValor;

/**
 *
 * @author samas
 */
public abstract class ActivoPropiedadReader {

    private final String clavePizarra;
    private final List<ActivoPropiedad> indexList = new ArrayList<>();
    private final HashMap<ActivoPropiedad, ActivoPropiedadValor> propiedadMap = new HashMap<>();

    public ActivoPropiedadReader(String clavePizarra) {
        this.clavePizarra = clavePizarra;
    }

    public void addPropiedadIndice(ActivoPropiedad indice) {
        indexList.add(indice);
    }

    public void addPropiedadesIndiceFromList(List<ActivoPropiedad> list) {
        indexList.addAll(list);
    }

    public ActivoPropiedadValor getPropiedadValor(ActivoPropiedad indice) {
        return propiedadMap.get(indice);
    }

    public abstract void execute() throws FileNotFoundException, IOException;

}
