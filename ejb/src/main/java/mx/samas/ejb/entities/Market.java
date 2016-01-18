/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 */
@Entity
@XmlRootElement
public class Market implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    
    @OneToOne
    private DenominatorCurrency currency;   
    
    @ManyToMany
    private List <SecurityClass> securities;
    
    @ManyToMany
    private List <Broker> brokers;
    
    

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
        if (!(object instanceof Market)) {
            return false;
        }
        Market other = (Market) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Market[ id=" + id + " ]";
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the currency
     */
    public DenominatorCurrency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(DenominatorCurrency currency) {
        this.currency = currency;
    }

    /**
     * @return the securities
     */
    @XmlTransient
    public List <SecurityClass> getSecurities() {
        return securities;
    }

    /**
     * @param securities the securities to set
     */
    public void setSecurities(List <SecurityClass> securities) {
        this.securities = securities;
    }

    /**
     * @return the brokers
     */
    @XmlTransient
    public List <Broker> getBrokers() {
        return brokers;
    }

    /**
     * @param brokers the brokers to set
     */
    public void setBrokers(List <Broker> brokers) {
        this.brokers = brokers;
    }
    
}
