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
 *
 * Si es discretionary, entonces activeComission es una comision devengada, sino
 * es transaccional
 */
@Entity
public class PortfolioAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private Boolean discretionary;

    private Double activeComission;

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
     * @return the activeComission
     */
    public Double getActiveComission() {
        return activeComission;
    }

    /**
     * @param activeComission the activeComission to set
     */
    public void setActiveComission(Double activeComission) {
        this.activeComission = activeComission;
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

}
