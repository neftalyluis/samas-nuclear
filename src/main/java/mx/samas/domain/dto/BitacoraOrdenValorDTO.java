/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.Date;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * Objeto para describir elvalor de una Transaccion perteneciente a una Orden
 *
 * @author samas
 */
public class BitacoraOrdenValorDTO {

    private Long transaccionId;
    private Long titulos;
    private Double efectivo;
    /**
     * 2.- Momento en el que se ejecuta la orden
     */
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaEjecucion;
    /**
     * 3.- Dia que se Liquida
     */
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaLiquidacion;
    /**
     * 1.- Momento que se (asenta) ingresa la orden
     */
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    /**
     * @return the transaccionId
     */
    public Long getTransaccionId() {
        return transaccionId;
    }

    /**
     * @param transaccionId the transaccionId to set
     */
    public void setTransaccionId(Long transaccionId) {
        this.transaccionId = transaccionId;
    }

    /**
     * @return the titulos
     */
    public Long getTitulos() {
        return titulos;
    }

    /**
     * @param titulos the titulos to set
     */
    public void setTitulos(Long titulos) {
        this.titulos = titulos;
    }

    /**
     * @return the efectivo
     */
    public Double getEfectivo() {
        return efectivo;
    }

    /**
     * @param efectivo the efectivo to set
     */
    public void setEfectivo(Double efectivo) {
        this.efectivo = efectivo;
    }

    /**
     * @return the fechaEjecucion
     */
    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    /**
     * @param fechaEjecucion the fechaEjecucion to set
     */
    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    /**
     * @return the fechaLiquidacion
     */
    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    /**
     * @param fechaLiquidacion the fechaLiquidacion to set
     */
    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

}
