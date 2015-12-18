/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author neftaly
 */
@Entity
public class Equity extends Asset implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean privateMarket;
    private Boolean fund;
    /**
     * Solo si fund es verdadero, sino NULL
     */
    private Double fee;

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
        if (!(object instanceof Equity)) {
            return false;
        }
        Equity other = (Equity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Equity[ id=" + id + " ]";
    }

    /**
     * @return the privateMarket
     */
    public Boolean getPrivateMarket() {
        return privateMarket;
    }

    /**
     * @param privateMarket the privateMarket to set
     */
    public void setPrivateMarket(Boolean privateMarket) {
        this.privateMarket = privateMarket;
    }

    /**
     * @return the fund
     */
    public Boolean getFund() {
        return fund;
    }

    /**
     * @param fund the fund to set
     */
    public void setFund(Boolean fund) {
        this.fund = fund;
    }

    /**
     * @return the fee
     */
    public Double getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(Double fee) {
        this.fee = fee;
    }
    
}
