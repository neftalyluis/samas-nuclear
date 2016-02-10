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
    private Broker broker;

    @ManyToOne
    private PortfolioVector portfolio;

    @ManyToOne
    private Transaction transaction;
    /**
     * Client Gvt Portfolio, osea quien dirige la transaccion.
     */
    @ManyToOne
    private SourceOwner transactionSource;

    private Long quantity;

    private Double price;

    private Double cashFlow;

    /**
     * Comision de Cliente
     */
    private Double activeComission;

    /**
     * A lo mejor instanciar la comision de broker
     *
     */
    private Double passiveComission;

    /**
     * Flujo de titulos
     */
    private Double quantityFlow;

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
     * @return the broker
     */
    public Broker getBroker() {
        return broker;
    }

    /**
     * @param broker the broker to set
     */
    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    /**
     * @return the portfolio
     */
    public PortfolioVector getPortfolio() {
        return portfolio;
    }

    /**
     * @param portfolio the portfolio to set
     */
    public void setPortfolio(PortfolioVector portfolio) {
        this.portfolio = portfolio;
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
     * @return the quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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
     * @return the cashFlow
     */
    public Double getCashFlow() {
        return cashFlow;
    }

    /**
     * @param cashFlow the cashFlow to set
     */
    public void setCashFlow(Double cashFlow) {
        this.cashFlow = cashFlow;
    }

    /**
     * @return the activeComission
     */
    public Double getActiveComission() {
        return activeComission;
    }

    /**
     * @param activeComission the activeComission to set
     */
    public void setActiveComission(Double activeComission) {
        this.activeComission = activeComission;
    }

    /**
     * @return the passiveComission
     */
    public Double getPassiveComission() {
        return passiveComission;
    }

    /**
     * @param passiveComission the passiveComission to set
     */
    public void setPassiveComission(Double passiveComission) {
        this.passiveComission = passiveComission;
    }

    /**
     * @return the quantityFlow
     */
    public Double getQuantityFlow() {
        return quantityFlow;
    }

    /**
     * @param quantityFlow the quantityFlow to set
     */
    public void setQuantityFlow(Double quantityFlow) {
        this.quantityFlow = quantityFlow;
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
     * @return the transactionSource
     */
    public SourceOwner getTransactionSource() {
        return transactionSource;
    }

    /**
     * @param transactionSource the transactionSource to set
     */
    public void setTransactionSource(SourceOwner transactionSource) {
        this.transactionSource = transactionSource;
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
