/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author samas
 */
@Entity
public class VectorPosicionCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Double valuacion;

    @ManyToOne
    private Portafolio cuenta;

    @ManyToOne
    private Activo activo;

    /**
     * Reporto y en Prenda, si es verdadero entonces existe un Activo que los
     * respalda de lo contrario es quirografario
     */
    private Boolean bursatilizado;

    private Boolean impuesto;

    @ManyToOne
    private Activo moneda;

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
        if (!(object instanceof VectorPosicionCredito)) {
            return false;
        }
        VectorPosicionCredito other = (VectorPosicionCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.VectorPosicionCredito[ id=" + id + " ]";
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the valuacion
     */
    public Double getValuacion() {
        return valuacion;
    }

    /**
     * @param valuacion the valuacion to set
     */
    public void setValuacion(Double valuacion) {
        this.valuacion = valuacion;
    }

    /**
     * @return the cuenta
     */
    public Portafolio getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Portafolio cuenta) {
        this.cuenta = cuenta;
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
     * @return the bursatilizado
     */
    public Boolean getBursatilizado() {
        return bursatilizado;
    }

    /**
     * @param bursatilizado the bursatilizado to set
     */
    public void setBursatilizado(Boolean bursatilizado) {
        this.bursatilizado = bursatilizado;
    }

    /**
     * @return the impuesto
     */
    public Boolean getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(Boolean impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * @return the moneda
     */
    public Activo getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Activo moneda) {
        this.moneda = moneda;
    }
}
