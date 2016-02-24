/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 *
 * Entidad que engloba las propiedades imperativas para los bonos
 */
@Entity
@XmlRootElement
public class Bond extends Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double spreadFixed;
    @Temporal(javax.persistence.TemporalType.DATE)

    /* maturityDate puede ser eliminada ya que la última entrada en cashflowDate
    es el vencimiento, dónde paga cupón y amortiza. */
    private Date maturityDate;
    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL)
    private List<CashflowDate> cashflowDate;

    @OneToOne
    private DayCount dayCount;

    @ManyToOne
    private ReferenceRate referenceRate;

    @OneToOne
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
     * @return the cashflowDate
     */
    @XmlTransient
    public List<CashflowDate> getCashflowDate() {
        return cashflowDate;
    }

    /**
     * @param cashflowDate the cashflowDate to set
     */
    public void setCashflowDate(List<CashflowDate> cashflowDate) {
        this.cashflowDate = cashflowDate;
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

}
