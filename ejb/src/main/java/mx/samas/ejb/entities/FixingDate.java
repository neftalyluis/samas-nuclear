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
public class FixingDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private BondType bond;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fixingDate;

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
        if (!(object instanceof FixingDate)) {
            return false;
        }
        FixingDate other = (FixingDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.FixingDate[ id=" + id + " ]";
    }

    /**
     * @return the bond
     */
    public BondType getBond() {
        return bond;
    }

    /**
     * @param bond the bond to set
     */
    public void setBond(BondType bond) {
        this.bond = bond;
    }

    /**
     * @return the fixingDate
     */
    public Date getFixingDate() {
        return fixingDate;
    }

    /**
     * @param fixingDate the fixingDate to set
     */
    public void setFixingDate(Date fixingDate) {
        this.fixingDate = fixingDate;
    }

}
