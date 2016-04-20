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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alfonso
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SliceVector.getSlicesFromStrategy", query = "SELECT s FROM SliceVector s WHERE s.strategy.id = :id")
})
public class SliceVector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne
    private Asset asset;

    @ManyToOne
    private Fungibility fungibility;

    @ManyToOne
    private Strategy strategy;

    private Double targetAllocation;

    public SliceVector() {
    }

    public SliceVector(Date d, Asset a, Strategy s, Double target) {
        this.asset = a;
        this.dateTime = d;
        this.strategy = s;
        this.targetAllocation = target;
    }

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
        if (!(object instanceof SliceVector)) {
            return false;
        }
        SliceVector other = (SliceVector) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.SliceVector[ id=" + getId() + " ]";
    }

    /**
     * @return the asset
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * @param id
     */
    public void setAsset(Asset id) {
        this.asset = id;
    }

    /**
     * @return the strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
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
     * @return the fungibility
     */
    public Fungibility getFungibility() {
        return fungibility;
    }

    /**
     * @param fungibility the fungibility to set
     */
    public void setFungibility(Fungibility fungibility) {
        this.fungibility = fungibility;
    }

    /**
     * @return the targetAllocation
     */
    public Double getTargetAllocation() {
        return targetAllocation;
    }

    /**
     * @param targetAllocation the targetAllocation to set
     */
    public void setTargetAllocation(Double targetAllocation) {
        this.targetAllocation = targetAllocation;
    }

}
