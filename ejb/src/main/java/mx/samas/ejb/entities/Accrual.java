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
 * @author neftaly Accrual acumula en el proceso de cierre y es en función a la
 * posición del dia que devengan impuestos, comisiones y/o solicitudes del
 * cliente como lo expresa el PositionVector de ese día .
 */
@Entity
@XmlRootElement
public class Accrual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTime;

    @ManyToOne
    private PortfolioAccount contract;

    /**
     * Los dueños de AccrualType Gobierno.- Impuestos; Negocio.- Comisiones;
     * Cliente.-Solicitudes de liquidez
     *
     */
    @ManyToOne
    private SourceOwner accrualOwner;
    
    @ManyToOne
    private DenominatorCurrency currencyDenomination;

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
        if (!(object instanceof Accrual)) {
            return false;
        }
        Accrual other = (Accrual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.BusinessAccrual[ id=" + id + " ]";
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
     * @return the accrualOwner
     */
    public SourceOwner getAccrualOwner() {
        return accrualOwner;
    }

    /**
     * @param accrualOwner the accrualOwner to set
     */
    public void setAccrualOwner(SourceOwner accrualOwner) {
        this.accrualOwner = accrualOwner;
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
     * @return the currencyDenomination
     */
    public DenominatorCurrency getCurrencyDenomination() {
        return currencyDenomination;
    }

    /**
     * @param currencyDenomination the currencyDenomination to set
     */
    public void setCurrencyDenomination(DenominatorCurrency currencyDenomination) {
        this.currencyDenomination = currencyDenomination;
    }

}
