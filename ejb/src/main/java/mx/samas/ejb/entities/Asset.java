/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author alfonso
 */


@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TICKER"})})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticker;
    private String name;
    @ManyToOne
    private SecurityClass tv;
    @ManyToOne
    private Issuer issuer;
    private String series;
    private String isin;
    @ManyToOne
    private DenominatorCurrency currencyDenomination;
    
    /**
     * "tickSize" es lo que viene siendo la "puja"
     */
    private Double tickSize;
    
    /* Si «settlementTimes» a ser constante, no va aquí; este es un campo
    relevante para operar en mercado (un método). Si es variable -- esto es, aquí
    sólo de define el valor por defecto -- puede caber en «Asset». No obstante,
    existe el riesgo de generar un comportamiento indeseado por la comodiad de
    de evitar declarar explícitamente la fecha valor  -- «settlementTimes» -- para
    cada operación. Quizás se le puede dar al usuario la opción -- de pedir su
    declaración explícita; esto sería por activo.    
    */
    
    @ManyToOne
    private SettlementTimes settlementTimes;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<AssetVector> vectors;

    public Asset() {
        this.vectors = new LinkedList<>();
    }

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
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Asset[ id=" + getId() + " ]";
    }

    /**
     * @return the ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * @param ticker the ticker to set
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the tv
     */
    public SecurityClass getTv() {
        return tv;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(String tv) {
        this.setTv(tv);
    }

    /**
     * @return the isin
     */
    public String getIsin() {
        return isin;
    }

    /**
     * @param isin the isin to set
     */
    public void setIsin(String isin) {
        this.isin = isin;
    }

    /**
     * @return the vectors
     */
    public List<AssetVector> getVectors() {
        return vectors;
    }

    /**
     * @param vectors the vectors to set
     */
    public void setVectors(List<AssetVector> vectors) {
        this.vectors = vectors;
    }

    /**
     * @return the issuer
     */
    public Issuer getIssuer() {
        return issuer;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(String issuer) {
        this.setIssuer(issuer);
    }

    /**
     * @return the series
     */
    public String getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * @return the tickSize
     */
    public Double getTickSize() {
        return tickSize;
    }

    /**
     * @param tickSize the tickSize to set
     */
    public void setTickSize(Double tickSize) {
        this.tickSize = tickSize;
    }

    /**
     * @return the settlementTimes
     */
    public SettlementTimes getSettlementTimes() {
        return settlementTimes;
    }

    /**
     * @param settlementTimes the settlementTimes to set
     */
    public void setSettlementTimes(SettlementTimes settlementTimes) {
        this.settlementTimes = settlementTimes;
    }

    /**
     * @return the currencyDenomination
     */
    public DenominatorCurrency getCurrencyDenomination() {
        return currencyDenomination;
    }

    /**
     * @param currencyDenomination the currencyDenomination to set
     */
    public void setCurrencyDenomination(DenominatorCurrency currencyDenomination) {
        this.currencyDenomination = currencyDenomination;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(SecurityClass tv) {
        this.tv = tv;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

}
