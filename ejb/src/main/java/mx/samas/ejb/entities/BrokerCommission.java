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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 *
 * Esta entidad describe los tipos de comision para todos los agentes que operan
 * en los portafolios
 */
@Entity
@XmlRootElement
public class BrokerCommission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Broker> broker;

    private Double transactionalCommission;

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
        if (!(object instanceof BrokerCommission)) {
            return false;
        }
        BrokerCommission other = (BrokerCommission) object;
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
     * @return the transactionalCommission
     */
    public Double getTransactionalCommission() {
        return transactionalCommission;
    }

    /**
     * @param transactionalCommission the transactionalCommission to set
     */
    public void setTransactionalCommission(Double transactionalCommission) {
        this.transactionalCommission = transactionalCommission;
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

    /**
     * @return the broker
     */
    @XmlTransient
    public List<Broker> getBroker() {
        return broker;
    }

    /**
     * @param broker the broker to set
     */
    public void setBroker(List<Broker> broker) {
        this.broker = broker;
    }

}
