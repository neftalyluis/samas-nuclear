/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.HashMap;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.ActivoPropiedadValor;

/**
 *
 * @author samas
 */
public class ActivoPropiedadValorDTO {

    private Activo activo;
    private HashMap<ActivoPropiedad, ActivoPropiedadValor> propiedades;

    /**
     * @return the activo
     */
    public Activo getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    /**
     * @return the propiedades
     */
    public HashMap<ActivoPropiedad, ActivoPropiedadValor> getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(HashMap<ActivoPropiedad, ActivoPropiedadValor> propiedades) {
        this.propiedades = propiedades;
    }
}
