/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String nombre;
    /**
     * 2.- Momento en el que se ejecuta la orden
     */
    private LocalDateTime fechaEjecucion;
    /**
     * 3.- Dia que se Liquida
     */
    private LocalDate fechaLiquidacion;
    /**
     * 1.- Momento que se (asenta) ingresa la orden
     */
    private LocalDateTime fechaIngreso;
    
    public BitacoraOrdenValorDTO(Long id, String nombre){
        this.transaccionId = id;
        this.nombre = nombre;
    }

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
    public LocalDateTime getFechaEjecucion() {
        return fechaEjecucion;
    }

    /**
     * @param fechaEjecucion the fechaEjecucion to set
     */
    public void setFechaEjecucion(LocalDateTime fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    /**
     * @return the fechaLiquidacion
     */
    public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    /**
     * @param fechaLiquidacion the fechaLiquidacion to set
     */
    public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    /**
     * @return the fechaIngreso
     */
    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

}
