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

/**
 *
 * @author neftaly
 */
//un cliente puede tener muchos portafolios en un contrato y que el pago de 
//dividendo/cupon/rendimiento se asigna a cada portafolio en funcion de los 
//titulos que tiene
@Entity
public class PortfolioVector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "portfolios")
    private List<Client> clients;

    @ManyToOne
    private Strategy strategy;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne
    private PortfolioAccount contract;

    private Double comission;

    @ManyToOne
    private ComissionType comissionType;

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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortfolioVector)) {
            return false;
        }
        PortfolioVector other = (PortfolioVector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Portfolio[ id=" + id + " ]";
    }

    /**
     * @return the clients
     */
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
     * @return the comissionType
     */
    public ComissionType getComissionType() {
        return comissionType;
    }

    /**
     * @param comissionType the comissionType to set
     */
    public void setComissionType(ComissionType comissionType) {
        this.comissionType = comissionType;
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
     * @return the comission
     */
    public Double getComission() {
        return comission;
    }

    /**
     * @param comission the comission to set
     */
    public void setComission(Double comission) {
        this.comission = comission;
    }

}
