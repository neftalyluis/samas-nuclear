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
public class VectorPosicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    private Portafolio portafolio;

    @ManyToOne
    private Activo activo;

    private Long cantidad;
    
    private Double valuacion;
    
    private Boolean enPrenda;
    
    private Boolean reporto;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;

    /**
     * Si es collateral esta posicion es la prenda que respalda el credito; de
     * no existir prenda en un credito en un quirografario
     *
     */
    private Boolean colateral;

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
        if (!(object instanceof VectorPosicion)) {
            return false;
        }
        VectorPosicion other = (VectorPosicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.VectorPosicion[ id=" + id + " ]";
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
     * @return the cantidad
     */
    public Long getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the colateral
     */
    public Boolean getColateral() {
        return colateral;
    }

    /**
     * @param colateral the colateral to set
     */
    public void setColateral(Boolean colateral) {
        this.colateral = colateral;
    }

    /**
     * @return the portafolio
     */
    public Portafolio getPortafolio() {
        return portafolio;
    }

    /**
     * @param portafolio the portafolio to set
     */
    public void setPortafolio(Portafolio portafolio) {
        this.portafolio = portafolio;
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
     * @return the enPrenda
     */
    public Boolean getEnPrenda() {
        return enPrenda;
    }

    /**
     * @param enPrenda the enPrenda to set
     */
    public void setEnPrenda(Boolean enPrenda) {
        this.enPrenda = enPrenda;
    }

    /**
     * @return the reporto
     */
    public Boolean getReporto() {
        return reporto;
    }

    /**
     * @param reporto the reporto to set
     */
    public void setReporto(Boolean reporto) {
        this.reporto = reporto;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
