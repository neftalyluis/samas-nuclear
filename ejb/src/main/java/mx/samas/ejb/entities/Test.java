/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author neftaly
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Test(){
        
    }
    
    public Test(String testName, Date initialDate, Date finalDate) {
        this.testName = testName;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }
    
    private String testName;
    
    @OneToMany
    private List<PortfolioAccount> accounts;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date initialDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finalDate;
    
    @OneToMany
    private List<TestPositionVector> results;

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
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Test[ id=" + id + " ]";
    }

    /**
     * @return the testName
     */
    public String getTestName() {
        return testName;
    }

    /**
     * @param testName the testName to set
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return the accounts
     */
    public List<PortfolioAccount> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(List<PortfolioAccount> accounts) {
        this.accounts = accounts;
    }

    /**
     * @return the initialDate
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * @param initialDate the initialDate to set
     */
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * @return the finalDate
     */
    public Date getFinalDate() {
        return finalDate;
    }

    /**
     * @param finalDate the finalDate to set
     */
    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    /**
     * @return the results
     */
    public List<TestPositionVector> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(List<TestPositionVector> results) {
        this.results = results;
    }

}
