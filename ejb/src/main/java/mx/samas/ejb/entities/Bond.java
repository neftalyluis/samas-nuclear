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
import javax.persistence.Temporal;

/**
 *
 * @author neftaly
 */
@Entity
public class Bond extends Asset implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double spreadFixed;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date maturityDate;
    private CashflowDate cashflowDates;
    private DayCount dayCount;
    private DenominatorCurrency currencyDenomination;
    
    /**
     * Propiedades Distintivas
     */
    private Boolean callable;
    private Boolean amortizing;
    //Falta definir BondCollateral
    private BondCollateral collateralized;
    private TermStructure termStructure;
    
    
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
        if (!(object instanceof Bond)) {
            return false;
        }
        Bond other = (Bond) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Bond[ id=" + id + " ]";
    }

    /**
     * @return the spreadFixed
     */
    public Double getSpreadFixed() {
        return spreadFixed;
    }

    /**
     * @param spreadFixed the spreadFixed to set
     */
    public void setSpreadFixed(Double spreadFixed) {
        this.spreadFixed = spreadFixed;
    }

    /**
     * @return the maturityDate
     */
    public Date getMaturityDate() {
        return maturityDate;
    }

    /**
     * @param maturityDate the maturityDate to set
     */
    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    /**
     * @return the cashflowDates
     */
    public CashflowDate getCashflowDates() {
        return cashflowDates;
    }

    /**
     * @param cashflowDates the cashflowDates to set
     */
    public void setCashflowDates(CashflowDate cashflowDates) {
        this.cashflowDates = cashflowDates;
    }

    /**
     * @return the dayCount
     */
    public DayCount getDayCount() {
        return dayCount;
    }

    /**
     * @param dayCount the dayCount to set
     */
    public void setDayCount(DayCount dayCount) {
        this.dayCount = dayCount;
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

    /**
     * @return the callable
     */
    public Boolean getCallable() {
        return callable;
    }

    /**
     * @param callable the callable to set
     */
    public void setCallable(Boolean callable) {
        this.callable = callable;
    }

    /**
     * @return the amortizing
     */
    public Boolean getAmortizing() {
        return amortizing;
    }

    /**
     * @param amortizing the amortizing to set
     */
    public void setAmortizing(Boolean amortizing) {
        this.amortizing = amortizing;
    }

    /**
     * @return the collateralized
     */
    public BondCollateral getCollateralized() {
        return collateralized;
    }

    /**
     * @param collateralized the collateralized to set
     */
    public void setCollateralized(BondCollateral collateralized) {
        this.collateralized = collateralized;
    }

    /**
     * @return the termStructure
     */
    public TermStructure getTermStructure() {
        return termStructure;
    }

    /**
     * @param termStructure the termStructure to set
     */
    public void setTermStructure(TermStructure termStructure) {
        this.termStructure = termStructure;
    }
    
}
