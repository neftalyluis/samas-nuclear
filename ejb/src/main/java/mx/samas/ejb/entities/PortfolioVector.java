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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 */
//un cliente puede tener muchos portafolios en un contrato y que el pago de 
//dividendo/cupon/rendimiento se asigna a cada portafolio en funcion de los 
//titulos que tiene
// * Si es discretionary, entonces comission es una comision devengada, sino
// * es transaccional
@Entity
@XmlRootElement
public class PortfolioVector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "portfolios")
    private List<Client> clients;

    @ManyToOne
    private Strategy strategy;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTime;

    @ManyToOne
    private PortfolioAccount account;

    @ManyToOne
    private PortfolioStatus portfolioStatus;

    public Long getId() {
        return id;
    }

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
        if (!(object instanceof PortfolioVector)) {
            return false;
        }
        PortfolioVector other = (PortfolioVector) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Portfolio[ id=" + getId() + " ]";
    }

    /**
     * @return the clients
     */
    @XmlTransient
    public List<Client> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * @return the strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
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
     * @return the portfolioStatus
     */
    public PortfolioStatus getPortfolioStatus() {
        return portfolioStatus;
    }

    /**
     * @param portfolioStatus the portfolioStatus to set
     */
    public void setPortfolioStatus(PortfolioStatus portfolioStatus) {
        this.portfolioStatus = portfolioStatus;
    }

    /**
     * @return the account
     */
    public PortfolioAccount getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(PortfolioAccount account) {
        this.account = account;
    }

}
