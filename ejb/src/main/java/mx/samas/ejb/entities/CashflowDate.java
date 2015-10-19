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
 */
@Entity
public class CashflowDate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Bond bond;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date payDate;
    /**
     * 1- Interest
     * 0.- Principal
     */
    private Boolean interest;

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
        if (!(object instanceof CashflowDate)) {
            return false;
        }
        CashflowDate other = (CashflowDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.CouponDate[ id=" + id + " ]";
    }

    /**
     * @return the bond
     */
    public Bond getBond() {
        return bond;
    }

    /**
     * @param bond the bond to set
     */
    public void setBond(Bond bond) {
        this.bond = bond;
    }

    /**
     * @return the payDate
     */
    public Date getPayDay() {
        return payDate;
    }

    /**
     * @param payDay the payDate to set
     */
    public void setPayDay(Date payDay) {
        this.payDate = payDay;
    }

    /**
     * @return the interest
     */
    public Boolean getCouponPrincipal() {
        return interest;
    }

    /**
     * @param couponPrincipal the interest to set
     */
    public void setCouponPrincipal(Boolean couponPrincipal) {
        this.interest = couponPrincipal;
    }
    
}
