/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 *
 * Campos:
 *
 * TV == Security Class; EMISORA == Issuer; SERIE == java.lang.String;
 *
 * Esta entidad es la entidad padre en la que se basan los cuatro tipos de
 * activos Basicamente son las propiedades que tienen todos los Activos
 * existentes
 *
 * 
 * Boolean ShortSale 
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findByTicker", query = "SELECT a FROM Asset a WHERE a.ticker = :ticker")

})
public abstract class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Ticker> ticker;

    /**
     * Tipo Valor
     */
    
    private String securityClass;

    /**
     * Emisora
     */
    @ManyToOne
    private Issuer issuer;

    /**
     * Serie
     */
    private String series;

    private String isin;

    @ManyToOne
    private DenominatorCurrency currencyDenomination;

    /**
     * Si es verdadero, entonces se asocia ese asset a una comision
     * (pasiva::Broker y activa::Client)
     */
    private Boolean comission;

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
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.NewAsset[ id=" + id + " ]";
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
     * @return the issuer
     */
    public Issuer getIssuer() {
        return issuer;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
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
     * @return the vectors
     */
    @XmlTransient
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
     * @return the comission
     */
    public Boolean getComission() {
        return comission;
    }

    /**
     * @param comission the comission to set
     */
    public void setComission(Boolean comission) {
        this.comission = comission;
    }

    /**
     * @return the ticker
     */
    public List<Ticker> getTicker() {
        return ticker;
    }

    /**
     * @param ticker the ticker to set
     */
    public void setTicker(List<Ticker> ticker) {
        this.ticker = ticker;
    }

    /**
     * @return the securityClass
     */
    public String getSecurityClass() {
        return securityClass;
    }

    /**
     * @param securityClass the securityClass to set
     */
    public void setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
    }

}
