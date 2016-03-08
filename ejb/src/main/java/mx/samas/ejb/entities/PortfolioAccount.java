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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Si es discretionary, entonces activeCommission es una comision devengada,
 * sino es transaccional
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortfolioAccount.findByAccountNumber", query = "SELECT pa FROM PortfolioAccount pa WHERE pa.accountNumber= :account")
})
public class PortfolioAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numero de cuenta
     */
    private String accountNumber;

    /**
     * Se refiere a que si esta contrato tiene comision discrecional
     */
    private Boolean discretionary;

    /**
     * La comision activa
     */
    private Double activeCommission;

    /**
     * Indica si esta cuenta acepta ventas en corto
     */
    private Boolean shortSale;

    /**
     * El banco al cual pertenece esta cuenta
     */
    @ManyToOne
    private Bank bank;

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
        if (!(object instanceof PortfolioAccount)) {
            return false;
        }
        PortfolioAccount other = (PortfolioAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Contract[ id=" + id + " ]";
    }

    /**
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the activeCommission
     */
    public Double getActiveCommission() {
        return activeCommission;
    }

    /**
     * @param activeCommission the activeCommission to set
     */
    public void setActiveCommission(Double activeCommission) {
        this.activeCommission = activeCommission;
    }

    /**
     * @return the discretionary
     */
    public Boolean getDiscretionary() {
        return discretionary;
    }

    /**
     * @param discretionary the discretionary to set
     */
    public void setDiscretionary(Boolean discretionary) {
        this.discretionary = discretionary;
    }

    /**
     * @return the shortSale
     */
    public Boolean getShortSale() {
        return shortSale;
    }

    /**
     * @param shortSale the shortSale to set
     */
    public void setShortSale(Boolean shortSale) {
        this.shortSale = shortSale;
    }

}
