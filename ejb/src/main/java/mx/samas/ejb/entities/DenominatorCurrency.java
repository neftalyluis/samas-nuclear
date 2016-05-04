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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Esta entidad sirve como relacion con los Assets, para reflejar la moneda en
 * la que cotizan
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DenominatorCurrency.findByTicker", query = "SELECT dc FROM DenominatorCurrency dc WHERE dc.currency.ticker= :ticker")

})
public class DenominatorCurrency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Asset currency;

    public DenominatorCurrency() {

    }

    public DenominatorCurrency(Asset currency) {
        this.currency = currency;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DenominatorCurrency)) {
            return false;
        }
        DenominatorCurrency other = (DenominatorCurrency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.DenominatorCurrency[ id=" + id + " ]";
    }

    /**
     * @return the currency
     */
    public Asset getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Asset currency) {
        this.currency = currency;
    }

}
