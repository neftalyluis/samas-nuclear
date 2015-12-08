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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author neftaly
 */
@Entity
public class BondRevisable extends Bond implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private DayCount dayCount;
    @OneToOne
    private FixingDate fixingDate;
    
    private Double faceValue;

    // Va en TyC
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date maturityDate;

    private Boolean amortizing;

    @ManyToOne
    private BondCollateral bondCollateral;

    /**
     * Este campo determina la regla cupón: tasa fija si "fija"; tasa revisable
     * si "!fija"
     */
    @OneToOne
    private ReferenceRate referenceRate;
    @OneToOne
    private TermStructure termStructure;

    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL)
    private List<CashflowDate> cashflowDates;
    private Boolean callable;

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
        Bond other = (Bond) object;
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
    @Override
    public void setCashflowDates(List<CashflowDate> cashflowDates) {
        this.cashflowDates = cashflowDates;
    }

    /**
     * @return the callable
     */
    @Override
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
    @Override
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
    @Override
    public BondCollateral getBondCollateral() {
        return bondCollateral;
    }

    /**
     * @param bondCollateral the bondCollateral to set
     */
    @Override
    public void setBondCollateral(BondCollateral bondCollateral) {
        this.bondCollateral = bondCollateral;
    }

    /**
     * @return the faceValue
     */
    @Override
    public Double getFaceValue() {
        return faceValue;
    }

    /**
     * @param faceValue the faceValue to set
     */
    @Override
    public void setFaceValue(Double faceValue) {
        this.faceValue = faceValue;
    }

}
