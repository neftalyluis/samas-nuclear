/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.projection;

import java.time.LocalDate;

/**
 *
 * @author samas
 */
public class EstrategiaModeloProjection {

    private LocalDate fechaCreacion;
    private String clavePizarra;
    private Double diana;

    /**
     * @param fechaCreacion Guardara la fecha actual.
     * @param clavePizarra Guardara una cadena.
     * @param diana Guardara un numero fraccionario (de hasta 15 digitos) que se ingrese desde la interfaz.
     */
    public EstrategiaModeloProjection(LocalDate fechaCreacion, String clavePizarra, Double diana) {
        this.fechaCreacion = fechaCreacion;
        this.clavePizarra = clavePizarra;
        this.diana = diana;
    }

    /**
     * @return the fechaCreacion
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the clavePizarra
     */
    public String getClavePizarra() {
        return clavePizarra;
    }

    /**
     * @param clavePizarra the clavePizarra to set
     */
    public void setClavePizarra(String clavePizarra) {
        this.clavePizarra = clavePizarra;
    }

    /**
     * @return the diana
     */
    public Double getDiana() {
        return diana;
    }

    /**
     * @param diana the diana to set
     */
    public void setDiana(Double diana) {
        this.diana = diana;
    }
}
