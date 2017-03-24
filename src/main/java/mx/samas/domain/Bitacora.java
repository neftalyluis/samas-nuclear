/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Entity
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    /**
     * Activo al cual se le esta registrando, si es que existe alguna
     */
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
    private Double importe;

    /**
     * Titulos totales
     */
    private Long titulos;

    @Enumerated
    private ConductorRiesgo conductorRiesgo;

    /**
     * Contrato del que deriva esta entrada
     */
    @ManyToOne
    private Cuenta destino;
    
    @ManyToOne
    private Cuenta origen;

    /**
     * Mercado en el cual se operó esta transaccion
     */
    @ManyToOne
    private Mercado mercado;

    @Column(unique = true, nullable = false)
    private String folioOperacion;

    private Double precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Bitacora[ id=" + id + " ]";
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
     * @return the folioOperacion
     */
    public String getFolioOperacion() {
        return folioOperacion;
    }

    /**
     * @param folioOperacion the folioOperacion to set
     */
    public void setFolioOperacion(String folioOperacion) {
        this.folioOperacion = folioOperacion;
    }

    /**
     * @return the importe
     */
    public Double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(Double importe) {
        this.importe = importe;
    }

    /**
     * @return the conductorRiesgo
     */
    public ConductorRiesgo getConductorRiesgo() {
        return conductorRiesgo;
    }

    /**
     * @param conductorRiesgo the conductorRiesgo to set
     */
    public void setConductorRiesgo(ConductorRiesgo conductorRiesgo) {
        this.conductorRiesgo = conductorRiesgo;
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
     * @return the destino
     */
    public Cuenta getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Cuenta destino) {
        this.destino = destino;
    }

    /**
     * @return the origen
     */
    public Cuenta getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Cuenta origen) {
        this.origen = origen;
    }

}
