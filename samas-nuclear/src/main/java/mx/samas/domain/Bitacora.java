/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author samas
 */
@Entity
public class Bitacora extends ParentModel {

    /**
     * Contructor generico
     */
    public Bitacora() {

    }

    public Bitacora(Date fechaEjecucion, Date fechaLiquidacion, Date fechaIngreso, Activo activo, Transaccion transaccion, Double precio, Double tasa, Double flujoEfectivo, Long flujoTitulos, Portafolio contrato, Mercado mercado) {
        this.fechaEjecucion = fechaEjecucion;
        this.fechaLiquidacion = fechaLiquidacion;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.transaccion = transaccion;
        this.precio = precio;
        this.tasa = tasa;
        this.flujoEfectivo = flujoEfectivo;
        this.flujoTitulos = flujoTitulos;
        this.contrato = contrato;
        this.mercado = mercado;
    }

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
     * Activo al cual se le esta registrando, si es que existe alguna
     */
    @JsonIgnore
    @ManyToOne
    private Activo activo;

    /**
     * Tipo de transaccion que se registra
     */
    @ManyToOne
    private Transaccion transaccion;
    /**
     * Precio total
     */
    private Double precio;

    /**
     * Tasa: Como precio en operacion en directo Tasa: Como tasa a devengar en
     * operaciones a credito
     */
    private Double tasa;

    /**
     * Es el flujo de efectivo de la operación
     */
    private Double flujoEfectivo;

    /**
     * Flujo de titulos
     */
    private Long flujoTitulos;

    /**
     * Contrato del que deriva esta entrada
     */
    @ManyToOne
    private Portafolio contrato;

    /**
     * Mercado en el cual se operó esta transaccion
     */
    @ManyToOne
    private Mercado mercado;
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
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the tasa
     */
    public Double getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    /**
     * @return the flujoEfectivo
     */
    public Double getFlujoEfectivo() {
        return flujoEfectivo;
    }

    /**
     * @param flujoEfectivo the flujoEfectivo to set
     */
    public void setFlujoEfectivo(Double flujoEfectivo) {
        this.flujoEfectivo = flujoEfectivo;
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
     * @return the contrato
     */
    public Portafolio getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(Portafolio contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the mercado
     */
    public Mercado getMercado() {
        return mercado;
    }

    /**
     * @param mercado the mercado to set
     */
    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    /**
     * @return the transaccion
     */
    public Transaccion getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

}
