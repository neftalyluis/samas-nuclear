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

/**
 *
 * @author neftaly
 *
 *
 */
@Entity
public class EquityShareFactor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equity equity;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTime;
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
        if (!(object instanceof EquityShareFactor)) {
            return false;
        }
        EquityShareFactor other = (EquityShareFactor) object;
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
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the equity
     */
    public Equity getEquity() {
        return equity;
    }

    /**
     * @param equity the equity to set
     */
    public void setEquity(Equity equity) {
        this.equity = equity;
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