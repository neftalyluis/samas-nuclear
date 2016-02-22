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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Aquí se registran los flujos que ocurren en el tiempo para cada operacion
 * donde haya un intercambio.
 */
@Entity
@XmlRootElement
public class Blotter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 2.- Momento en el que se ejecuta la orden
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date tradeDate;
    /**
     * 3.- Dia que se Liquida
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date settlementDate;
    /**
     * 1.- Momento que se (asenta) ingresa la orden
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inputDate;

    @ManyToOne
    private Asset asset;

    @ManyToOne
    private Transaction transaction;

    private Double price;

    /**
     * Tasa: Como precio en operacion en directo Tasa: Como tasa a devengar en
     * operaciones a credito
     */
    private Double rate;

    /**
     * Es el flujo de efectivo de la operación
     */
    private Double amount;
    
    /**
     * Flujo de titulos
     */
    private Double quantity;

    @ManyToOne
    private PortfolioAccount contract;

    @OneToOne
    private Market market;
    
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
        if (!(object instanceof Blotter)) {
            return false;
        }
        Blotter other = (Blotter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Bitacora[ id=" + id + " ]";
    }

    /**
     * @return the tradeDate
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * @param tradeDate the tradeDate to set
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * @return the settlementDate
     */
    public Date getSettlementDate() {
        return settlementDate;
    }

    /**
     * @param settlementDate the settlementDate to set
     */
    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    /**
     * @return the inputDate
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * @param inputDate the inputDate to set
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
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
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(Double rate) {
        this.rate = rate;
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
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
     * @return the market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * @param market the market to set
     */
    public void setMarket(Market market) {
        this.market = market;
    }

}
