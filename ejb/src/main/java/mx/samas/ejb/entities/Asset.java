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

/** Necesitamos crear varias entidades más, esto no agrega complejidad y asienta
 * las bases de las fungibilidades y la extensibilidad de SAMAS a futuro:
 * 
 * (1) «SecurityClass» estos son los tipos de valor
 *      Campos:
 *          + ID
 *          + code
 *          + description
 *          + «Lista de «Asset{Bond,Equity,Currency,Derivative}»» asociados a cada tipo valor
 *              Por ejemplo: 'Equity' tiene asociados '1' '1A' '1I' '1B' ...
 *                           'Bond' tiene asociados 'M', 'S', '91', ...
 *          + Tipo de Asset(Bond, Equity, etc, etc)
 * 
 * (2) «LegalEntity» (entidad/compañía emisora) ('LegalEntity' suena feo, sugieran)
 *      La justificación la existencia de esta entidad es que cataloga el universo
 *      de entidades emisoras -- los cuales llevan en sí un grado crediticio
 *      Campos:
 *          + ID
 *          + name (Razón social), ej. Petróleos Méxicanos
 *          + fiscalDomicile, ej. Torre Pémex
 *          + «Lista de códigos «CreditRating»» asociados, ej. [1,2,...] => 1:= (S&P,"AAA"), 2:=(Moody's, "Aaa"), ...
 *          + «Lista de códigos de «Issuer»» asociados, ej. 'PEMEX', 'PMX', ...
 *          
 * (3) «Issuer»
 *      De igual manera, esto va a servir para las fungibilidades
 *      Campos:
 *          + ID
 *          + code, ej. 'PEMEX', 'PMX', 'BONOS', 'AMX', ...
 *          Tarea:  Investigar si son necesarios los campos de 'Trato fiscal' y
 *                  'jurisdicción fiscal' a nivel «Issuer»; quizás es a nivel «SecurityClass»
 * 
 * (4) «Market»
 *      Este campo nos va a ayudar a saber dónde operar qué
 *      Campos:
 *          + ID
 *          + type := {'Exchage','OTC'}
 *          + code, ej. 'BMV', 'MEXDER', 'IEX', 'NYSE',... # Quizás asociado al protocolo FIX
 *          + Asociación a UN «DenominatorCurrency» para liquidación
 *          + *** Quizás se tenga que definir el sabor de FIX protocol para cada «Market»
 *          + «Lista de códigos «SecurityClass»» asociados que operan en este «Market»
 *          + «Lista de códigos «Broker»» asociados que agencian este «Market»
 *          
 *          Tarea:  Política de margen
 * 
 *      NB: Se tiene que asociar un campo en «Blotter» a esta entidad
 * 
 * (5) «CreditRating»
 *          Campos:
 *          + ID
 *          + Agency # ¿Crear una entidad que catalogue «Agency»? hmmm...
 *          + Grade
 * 
 *      Esta entidad generará una tabla así:
 * 
 *      |   ID  |   Agency  |   Grade   |
 *      |-------|-----------|-----------|
 *      |   1   |   S&P     |   AAA     |
 *      |   2   |   S&P     |   AA      |
 *      |   3   |   Moody's |   Aaa     |
 *      |   4   |   Moody's |   Aa      |
 *      ...etc
 * 
 *      Esta nueva entidad conjuga los cuatro campos en «BondVector»
 *      de «grade*» * := {SP,Moodys,Fitch,HR}
 * 
 *      Tarea:  Crear equivalencias de grados crediticios => en SAMAS inteligencia
 * 
* 
*/

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TICKER"})})
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticker;
    private String name;
    private String tv;
    private String issuer; // va atado a la nueva entidad «Issuer»
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
        return "mx.samas.entities.Asset[ id=" + id + " ]";
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
    public String getTv() {
        return tv;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(String tv) {
        this.tv = tv;
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
    public String getIssuer() {
        return issuer;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(String issuer) {
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

}
