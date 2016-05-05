/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly

 Devengo acumula en el proceso de cierre y es en función a la posición del dia
 que devengan impuestos, comisiones y/o solicitudes del cliente como lo
 expresa el PositionVector de ese día .
 */
@Entity
@XmlRootElement
public class Devengo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cantidad a devengar
     */
    private Double cantidad;

    /**
     * Fecha del registro
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRegistro;

    /**
     * A que portafolio le pertenece este devengo
     */
    @ManyToOne
    private PortafolioCuenta cuenta;

    /**
     * De quien proviene ese devengo
     */
    @ManyToOne
    private DuenoFuente duenoDevengo;

    /**
     * Moneda en el que esta valuado este devengo
     */
    @ManyToOne
    private DenominacionMoneda denominacion;

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
        if (!(object instanceof Devengo)) {
            return false;
        }
        Devengo other = (Devengo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.BusinessAccrual[ id=" + id + " ]";
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the cuenta
     */
    public PortafolioCuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(PortafolioCuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the duenoDevengo
     */
    public DuenoFuente getDuenoDevengo() {
        return duenoDevengo;
    }

    /**
     * @param duenoDevengo the duenoDevengo to set
     */
    public void setDuenoDevengo(DuenoFuente duenoDevengo) {
        this.duenoDevengo = duenoDevengo;
    }

    /**
     * @return the denominacion
     */
    public DenominacionMoneda getDenominacion() {
        return denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(DenominacionMoneda denominacion) {
        this.denominacion = denominacion;
    }
}
