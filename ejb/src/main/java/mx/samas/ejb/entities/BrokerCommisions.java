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
import javax.persistence.ManyToOne;

/**
 *
 * @author neftaly
 */
@Entity
public class BrokerCommisions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Broker broker;
    
    private Double transactionalComission;

    private String assetType;
    
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
        if (!(object instanceof BrokerCommisions)) {
            return false;
        }
        BrokerCommisions other = (BrokerCommisions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.BrokerCommisions[ id=" + id + " ]";
    }

    /**
     * @return the broker
     */
    public Broker getBroker() {
        return broker;
    }

    /**
     * @param broker the broker to set
     */
    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    /**
     * @return the transactionalComission
     */
    public Double getTransactionalComission() {
        return transactionalComission;
    }

    /**
     * @param transactionalComission the transactionalComission to set
     */
    public void setTransactionalComission(Double transactionalComission) {
        this.transactionalComission = transactionalComission;
    }

    /**
     * @return the assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * @param assetType the assetType to set
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    
}
