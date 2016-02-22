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
 */
@Entity
@XmlRootElement
public class PositionVector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne
    private PortfolioVector portfolioVector;

    @ManyToOne
    private Asset asset;

    private Long quantity;

    /**
     * Si es collateral esta posicion es la prenda que respalda el credito; de
     * no existir prenda en un credito en un quirografario
     *  
     */
    private Boolean collateral;

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
        if (!(object instanceof PositionVector)) {
            return false;
        }
        PositionVector other = (PositionVector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.PositionVector[ id=" + id + " ]";
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
     * @return the asset
     */
    public Asset getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    /**
     * @return the quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the portfolioVector
     */
    public PortfolioVector getPortfolioVector() {
        return portfolioVector;
    }

    /**
     * @param portfolioVector the portfolioVector to set
     */
    public void setPortfolioVector(PortfolioVector portfolioVector) {
        this.portfolioVector = portfolioVector;
    }

    /**
     * @return the collateral
     */
    public Boolean getCollateral() {
        return collateral;
    }

    /**
     * @param collateral the collateral to set
     */
    public void setCollateral(Boolean collateral) {
        this.collateral = collateral;
    }

}
