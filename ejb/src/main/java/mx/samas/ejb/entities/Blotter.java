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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "Blotter.BuyAndSellFromDateAndAccountWithAsset",
            query = "SELECT b FROM Blotter b JOIN b.transaction t WHERE "
            + "b.contract= :account "
            + "AND b.inputDate= :input "
            + "AND t.transactionSource.name= 'Portfolio' "
            + "AND (t.name= 'Compra' OR t.name= 'Venta'"
            + "AND b.asset= :asset)"),

    @NamedQuery(name = "Blotter.BuyAndSellFromDateAndAccountWith",
            query = "SELECT b FROM Blotter b JOIN b.transaction t WHERE "
            + "b.contract= :account "
            + "AND b.inputDate= :input "
            + "AND t.transactionSource.name= 'Portfolio' "
            + "AND (t.name= 'Compra' OR t.name= 'Venta'"
            + "AND b.asset= :asset)"),
    //Aplica para todos 
    //Todo lo que opere hoy, traeme todo lo que opere hoy que me liquide siguiente dia, 
    //
    @NamedQuery(name = "Blotter.flujosInternosPorDia", query = "SELECT b FROM Blotter b WHERE b.inputDate= :date")

})
public class Blotter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contructor generico
     */
    public Blotter() {

    }

    /**
     *
     * @param amount
     * @param asset
     * @param account
     * @param inputDate
     * @param market
     * @param price
     * @param quantity
     * @param rate
     * @param settlementDate
     * @param tradeDate
     * @param transaction
     */
    public Blotter(Double amount, Asset asset, PortfolioAccount account, Date inputDate, Market market, Double price, Long quantity, Double rate, Date settlementDate, Date tradeDate, Transaction transaction) {
        this.amount = amount;
        this.asset = asset;
        this.contract = account;
        this.inputDate = inputDate;
        this.market = market;
        this.price = price;
        this.quantity = quantity;
        this.rate = rate;
        this.settlementDate = settlementDate;
        this.tradeDate = tradeDate;
        this.transaction = transaction;
    }

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

    /**
     * Activo al cual se le esta registrando, si es que existe alguna
     */
    @ManyToOne
    private Asset asset;

    /**
     * Tipo de transaccion que se registra
     */
    @ManyToOne
    private Transaction transaction;

    /**
     * Precio total
     */
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
    private Long quantity;

    /**
     * Contrato del que deriva esta entrada
     */
    @ManyToOne
    private PortfolioAccount contract;

    /**
     * Mercado en el cual se operó esta transaccion
     */
    @ManyToOne
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
