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
import javax.persistence.OneToMany;

/**
 *
 * @author neftaly
 */
@Entity
public class LegalEntity implements Serializable {

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

    private String name;
    private String fiscalDomicile;
    
    @OneToMany
    private List<RatingGrade> rating;
    
    @OneToMany
    private List<Issuer> issuers;

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
        if (!(object instanceof LegalEntity)) {
            return false;
        }
        LegalEntity other = (LegalEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.LegalClass[ id=" + getId() + " ]";
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

    /**
     * @return the fiscalDomicile
     */
    public String getFiscalDomicile() {
        return fiscalDomicile;
    }

    /**
     * @param fiscalDomicile the fiscalDomicile to set
     */
    public void setFiscalDomicile(String fiscalDomicile) {
        this.fiscalDomicile = fiscalDomicile;
    }

    /**
     * @return the rating
     */
    public List<RatingGrade> getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(List<RatingGrade> rating) {
        this.rating = rating;
    }

    /**
     * @return the issuers
     */
    public List<Issuer> getIssuers() {
        return issuers;
    }

    /**
     * @param issuers the issuers to set
     */
    public void setIssuers(List<Issuer> issuers) {
        this.issuers = issuers;
    }

}
