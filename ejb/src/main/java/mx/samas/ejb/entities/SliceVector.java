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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author alfonso
 */
@Entity
public class SliceVector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    @OneToOne
    private AssetType asset;

    @ManyToOne
    private Fungible fungible;

    @ManyToOne
    private Strategy strategy;

    private Double targetAllocation;

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
        if (!(object instanceof SliceVector)) {
            return false;
        }
        SliceVector other = (SliceVector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.SliceVector[ id=" + id + " ]";
    }

    /**
     * @return the asset
     */
    public AssetType getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(AssetType asset) {
        this.asset = asset;
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
     * @return the fungible
     */
    public Fungible getFungible() {
        return fungible;
    }

    /**
     * @param fungible the fungible to set
     */
    public void setFungible(Fungible fungible) {
        this.fungible = fungible;
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
