/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly Entidad padre en la que se basan los cuatro tipos de activos,
 * esta incluyen las propiedades que tienen todos los Activos.
 *
 *
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

    /**
     * Nuevo objeto vacio
     */
    public Asset() {

    }

    /**
     * @param name El nombre del Activo
     * @param securityClass El tipo valor
     * @param issuer La emisora
     * @param series La serie
     * @param shortSale ¿Este activo se puede usar para ventas en corto?
     */
    public Asset(String name, String securityClass, Issuer issuer, String series, Boolean shortSale) {
        this.name = name;
        this.securityClass = securityClass;
        this.issuer = issuer;
        this.series = series;
        this.ticker = securityClass + "_" + issuer.getCode()+ "_" + series;
        this.shortSale = shortSale;
    }

    /**
     * Nombre del Activo
     */
    private String name;

    /**
     * Identificador de un activo, es la concatenacion de TV, Emisora y Serie
     */
    @Column(unique=true)
    private String ticker;

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

    /**
     * Identificador unico de un Asset, puede ser otra opcioón ademas del Ticker
     */
    @Column(unique=true)
    private String isin;

    @ManyToOne
    private DenominatorCurrency currencyDenomination;

    /**
     * "tickSize" es lo que viene siendo la "puja"
     *
     * Unidad para ofertar
     *
     * Puja minima,
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
    /**
     * Tiempo en que liquida
     */
    @ManyToOne
    private SettlementTimes settlementTimes;

    /**
     * Este valor indica si se puede usar este Asset para ventas en corto
     */
    private Boolean shortSale;

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
     * @return the shortSale
     */
    public Boolean getShortSale() {
        return shortSale;
    }

    /**
     * @param shortSale the shortSale to set
     */
    public void setShortSale(Boolean shortSale) {
        this.shortSale = shortSale;
    }

}
