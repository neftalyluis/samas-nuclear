/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Entity
public class VectorActivo extends ParentModel {

    @ManyToOne
    private Activo activo;

    private Double precioLimpio;
    
    @Lob
    private String propiedades;

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
     * @return the precioLimpio
     */
    public Double getPrecioLimpio() {
        return precioLimpio;
    }

    /**
     * @param precioLimpio the precioLimpio to set
     */
    public void setPrecioLimpio(Double precioLimpio) {
        this.precioLimpio = precioLimpio;
    }

}
