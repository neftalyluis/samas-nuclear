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
public class Bond extends Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private DayCount dayCount;
    
    private Double faceValue;

    // Va en TyC
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date maturityDate;

    private Boolean amortizing;

    @ManyToOne
    private BondCollateral bondCollateral;

    @OneToOne
    private TermStructure termStructure;

    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL)
    private List<CashflowDate> cashflowDates;
    private Boolean callable;

    public Bond() {
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bond)) {
            return false;
        }
        Bond other = (Bond) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Bond[ id=" + getId() + " ]";
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
        return getCallable();
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
        return getAmortizing();
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

    /**
     * @return the amortizing
     */
    public Boolean getAmortizing() {
        return amortizing;
    }

    /**
     * @return the callable
     */
    public Boolean getCallable() {
        return callable;
    }

}
