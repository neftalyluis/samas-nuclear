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
 *
 *
 */
@Entity
@XmlRootElement
public class AccionFactorConversion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Activo accion;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    /**
     * Si Split
     * 
     *      Q_0::Int * factor::Int = Q_1::Int 
     * 
     * Si Accretion 
     * 
     *      Q_0::Int / factor::Int = Q_1::Int
     * 
     * Si Dividend
     *      
     *      precio_0 + factor = precio_1
     * 
     * 
     
     */
    private Long factor; 
    
    private String factorType; // = {Split, Accretion, Dividend}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionFactorConversion)) {
            return false;
        }
        AccionFactorConversion other = (AccionFactorConversion) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.CorporateAction[ id=" + getId() + " ]";
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
     * @return the accion
     */
    public Activo getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(Activo accion) {
        this.accion = accion;
    }

    /**
     * @return the factor
     */
    public Long getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(Long factor) {
        this.factor = factor;
    }

    /**
     * @return the factorType
     */
    public String getFactorType() {
        return factorType;
    }

    /**
     * @param factorType the factorType to set
     */
    public void setFactorType(String factorType) {
        this.factorType = factorType;
    }
}