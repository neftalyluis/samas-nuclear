/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.Date;
import mx.samas.domain.VectorActivo;

/**
 *
 * @author samas
 */
public class VectorActivoDTO {

    private String clavePizarra;
    private VectorActivo vector;

    public VectorActivoDTO(String clavePizarra, Date fecha, Double precioLimpio) {
        this.clavePizarra = clavePizarra;
        this.vector = new VectorActivo(fecha, precioLimpio);
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
     * @return the vector
     */
    public VectorActivo getVector() {
        return vector;
    }

    /**
     * @param vector the vector to set
     */
    public void setVector(VectorActivo vector) {
        this.vector = vector;
    }

}