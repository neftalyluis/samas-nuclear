/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author samas
 */
@Entity
public class Accion extends Activo implements Serializable {

    private Boolean privada;
    private Boolean fondo;
    private Double comisionAnual;

    public Accion() {

    }

    public Accion(String tipoValor, String emisora, String serie, String isin,
            String nombre, TipoActivo tipo, Boolean ventaEnCorto,
            Double pujaMinima) {
        super(tipoValor, emisora, serie, isin, nombre, tipo, ventaEnCorto, pujaMinima);

    }

    /**
     * @return the privada
     */
    public Boolean getPrivada() {
        return privada;
    }

    /**
     * @param privada the privada to set
     */
    public void setPrivada(Boolean privada) {
        this.privada = privada;
    }

    /**
     * @return the fondo
     */
    public Boolean getFondo() {
        return fondo;
    }

    /**
     * @param fondo the fondo to set
     */
    public void setFondo(Boolean fondo) {
        this.fondo = fondo;
    }

    /**
     * @return the comisionAnual
     */
    public Double getComisionAnual() {
        return comisionAnual;
    }

    /**
     * @param comisionAnual the comisionAnual to set
     */
    public void setComisionAnual(Double comisionAnual) {
        this.comisionAnual = comisionAnual;
    }

}
