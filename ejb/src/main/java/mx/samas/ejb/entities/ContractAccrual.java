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
public class ContractAccrual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne
    private PortfolioAccount contract;

    @ManyToOne
    private Asset asset;

    @ManyToOne
    private Broker broker;

    @ManyToOne
    private Bank bank;

    @ManyToOne
    private AccrualType accrualType;

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
        if (!(object instanceof ContractAccrual)) {
            return false;
        }
        ContractAccrual other = (ContractAccrual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.PortfolioAccrual[ id=" + id + " ]";
    }

    /**
     * @return the contract
     */
    public PortfolioAccount getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(PortfolioAccount contract) {
        this.contract = contract;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
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
     * @return the broker
     */
    public Broker getBroker() {
        return broker;
    }

    /**
     * @param broker the broker to set
     */
    public void setBroker(Broker broker) {
        this.broker = broker;
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
     * @return the accrualType
     */
    public AccrualType getAccrualType() {
        return accrualType;
    }

    /**
     * @param accrualType the accrualType to set
     */
    public void setAccrualType(AccrualType accrualType) {
        this.accrualType = accrualType;
    }

}
