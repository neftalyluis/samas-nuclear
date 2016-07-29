/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Entity
public class Transaccion extends ParentModel {

    /**
     * Quien dirige la transaccion
     */
    @ManyToOne
    private DuenoFuente fuenteTransaccion;

    /**
     * Nombre de la Transaccion
     */
    private String nombre;

    /**
     * Si esta transaccion es a credito
     */
    private Boolean credito;

    /**
     * Indica si entran, salen o no hay flujo de titulos
     */
    private Long flujoTitulos;

    /**
     * Indica si entran, salen o no hay flujo de efectivo
     */
    private Long flujoEfectivo;

    /**
     * @return the fuenteTransaccion
     */
    public DuenoFuente getFuenteTransaccion() {
        return fuenteTransaccion;
    }

    /**
     * @param fuenteTransaccion the fuenteTransaccion to set
     */
    public void setFuenteTransaccion(DuenoFuente fuenteTransaccion) {
        this.fuenteTransaccion = fuenteTransaccion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the credito
     */
    public Boolean getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(Boolean credito) {
        this.credito = credito;
    }

    /**
     * @return the flujoTitulos
     */
    public Long getFlujoTitulos() {
        return flujoTitulos;
    }

    /**
     * @param flujoTitulos the flujoTitulos to set
     */
    public void setFlujoTitulos(Long flujoTitulos) {
        this.flujoTitulos = flujoTitulos;
    }

    /**
     * @return the flujoEfectivo
     */
    public Long getFlujoEfectivo() {
        return flujoEfectivo;
    }

    /**
     * @param flujoEfectivo the flujoEfectivo to set
     */
    public void setFlujoEfectivo(Long flujoEfectivo) {
        this.flujoEfectivo = flujoEfectivo;
    }
}
