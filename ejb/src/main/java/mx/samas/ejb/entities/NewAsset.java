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
public class NewAsset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


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
        if (!(object instanceof NewAsset)) {
            return false;
        }
        NewAsset other = (NewAsset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.NewAsset[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

//    /**
//     * @return the issuer
//     */
//    public Issuer getIssuer() {
//        return issuer;
//    }
//
//    /**
//     * @param issuer the issuer to set
//     */
//    public void setIssuer(Issuer issuer) {
//        this.issuer = issuer;
//    }
//
//    /**
//     * @return the series
//     */
//    public String getSeries() {
//        return series;
//    }
//
//    /**
//     * @param series the series to set
//     */
//    public void setSeries(String series) {
//        this.series = series;
//    }
//
//    /**
//     * @return the isin
//     */
//    public String getIsin() {
//        return isin;
//    }
//
//    /**
//     * @param isin the isin to set
//     */
//    public void setIsin(String isin) {
//        this.isin = isin;
//    }
//
//    /**
//     * @return the vectors
//     */
//    public List<AssetVector> getVectors() {
//        return vectors;
//    }
//
//    /**
//     * @param vectors the vectors to set
//     */
//    public void setVectors(List<AssetVector> vectors) {
//        this.vectors = vectors;
//    }

}
