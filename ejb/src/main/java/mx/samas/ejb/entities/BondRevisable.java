/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author neftaly
 */
@Entity
public class BondRevisable extends Bond implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @OneToOne
    private FixingDate fixingDate;
    
    /**
     * Este campo determina la regla cupón: tasa fija si "fija"; tasa revisable
     * si "!fija"
     */
    @OneToOne
    private ReferenceRate referenceRate;

    public BondRevisable() {
        this.cashflowDates = new LinkedList<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        BondRevisable other = (BondRevisable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Bond[ id=" + id + " ]";
    }

    /**
     * @return the cashflowDates
     */
    public List<CashflowDate> getCashflowDates() {
        return cashflowDates;
    }

    /**
     * @param cashflowDates the cashflowDates to set
     */
    public void setCashflowDates(List<CashflowDate> cashflowDates) {
        this.cashflowDates = cashflowDates;
    }

    /**
     * @return the callable
     */
    public Boolean isCallable() {
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
    public Boolean isAmortizing() {
        return amortizing;
    }

    /**
     * @param amortizing the amortizing to set
     */
    public void setAmortizing(Boolean amortizing) {
        this.amortizing = amortizing;
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
     * @return the referenceRate
     */
    public ReferenceRate getReferenceRate() {
        return referenceRate;
    }

    /**
     * @param referenceRate the referenceRate to set
     */
    public void setReferenceRate(ReferenceRate referenceRate) {
        this.referenceRate = referenceRate;
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
     * @return the fixingDate
     */
    public FixingDate getFixingDate() {
        return fixingDate;
    }

    /**
     * @param fixingDate the fixingDate to set
     */
    public void setFixingDate(FixingDate fixingDate) {
        this.fixingDate = fixingDate;
    }

    /**
     * @return the bondCollateral
     */
    public BondCollateral getBondCollateral() {
        return bondCollateral;
    }

    /**
     * @param bondCollateral the bondCollateral to set
     */
    public void setBondCollateral(BondCollateral bondCollateral) {
        this.bondCollateral = bondCollateral;
    }

    /**
     * @return the faceValue
     */
    public Double getFaceValue() {
        return faceValue;
    }

    /**
     * @param faceValue the faceValue to set
     */
    public void setFaceValue(Double faceValue) {
        this.faceValue = faceValue;
    }

}
