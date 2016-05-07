/*
 * To change this license header, choose License Headers in Project propiedades.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly 
 * 
 * Entidad padre en la que se basan los cuatro tipos de activos,
 * esta incluyen las propiedades que tienen todos los Activos.
 *
 *
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activo.buscarPorClavePizarra", query = "SELECT a FROM Activo a WHERE a.clavePizarra = :clavePizarra")

})
public class Activo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nuevo objeto vacio
     */
    public Activo() {

    }

    /**
     * @param nombre El nombre del Activo
     * @param tipoValor El tipo valor
     * @param emisora La emisora
     * @param serie La serie
     * @param ventaEnCorto ¿Este activo se puede usar para ventas en corto?
     */
    public Activo(String nombre, String tipoValor, Emisor emisora, String serie, Boolean ventaEnCorto) {
        this.nombre = nombre;
        this.tipoValor = tipoValor;
        this.emisora = emisora;
        this.serie = serie;
        this.clavePizarra = tipoValor + "_" + emisora.getCodigo()+ "_" + serie;
        this.ventaEnCorto = ventaEnCorto;
    }

    /**
     * Nombre del Activo
     */
    private String nombre;

    /**
     * Identificador de un activo, es la concatenacion de TV, Emisora y Serie
     */
    @Column(unique=true)
    private String clavePizarra;

    /**
     * Tipo Valor
     */
    private String tipoValor;

    /**
     * Emisora
     */
    @ManyToOne
    private Emisor emisora;

    /**
     * Serie
     */
    private String serie;

    /**
     * Identificador unico de un Activo, puede ser otra opcioón ademas del clavePizarra
     */
    @Column(unique=true)
    private String isin;

    @ManyToOne
    private DenominacionMoneda monedaDenominacion;

    /**
     * "pujaMinima" es lo que viene siendo la "puja"
     *
     * Unidad para ofertar
     *
     * Puja minima,
     */
    private Double pujaMinima;

    /* Si «fechaLiquidacion» a ser constante, no va aquí; este es un campo
     relevante para operar en mercado (un método). Si es variable -- esto es, aquí
     sólo de define el valor por defecto -- puede caber en «Activo». No obstante,
     existe el riesgo de generar un comportamiento indeseado por la comodiad de
     de evitar declarar explícitamente la fecha valor  -- «fechaLiquidacion» -- para
     cada operación. Quizás se le puede dar al usuario la opción -- de pedir su
     declaración explícita; esto sería por activo.    
     */
    /**
     * Tiempo en que liquida
     */
    @ManyToOne
    private FechaValor fechaLiquidacion;

    /**
     * Este valor indica si se puede usar este Activo para ventas en corto
     */
    private Boolean ventaEnCorto;

    
    @OneToMany(mappedBy = "activo", cascade = CascadeType.ALL)
    private List<VectorActivo> vectores;

    @OneToMany
    private List<ActivoPropiedadValor> propiedades;
    
    /**
     * Tipo de activo:
     * 
     * -Equity
     * -Bond
     * -Currency
     * -Derivative
     */
    @Enumerated(EnumType.STRING)
    private TipoActivo tipo;
    
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
        if (!(object instanceof Activo)) {
            return false;
        }
        Activo other = (Activo) object;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the clavePizarra
     */
    public String getClavePizarra() {
        return clavePizarra;
    }

    /**
     * @param clavePizarra the clavePizarra to set
     */
    public void setClavePizarra(String clavePizarra) {
        this.clavePizarra = clavePizarra;
    }

    /**
     * @return the tipoValor
     */
    public String getTipoValor() {
        return tipoValor;
    }

    /**
     * @param tipoValor the tipoValor to set
     */
    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    /**
     * @return the emisora
     */
    public Emisor getEmisora() {
        return emisora;
    }

    /**
     * @param emisora the emisora to set
     */
    public void setEmisora(Emisor emisora) {
        this.emisora = emisora;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
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
     * @return the monedaDenominacion
     */
    public DenominacionMoneda getMonedaDenominacion() {
        return monedaDenominacion;
    }

    /**
     * @param monedaDenominacion the monedaDenominacion to set
     */
    public void setMonedaDenominacion(DenominacionMoneda monedaDenominacion) {
        this.monedaDenominacion = monedaDenominacion;
    }

    /**
     * @return the pujaMinima
     */
    public Double getPujaMinima() {
        return pujaMinima;
    }

    /**
     * @param pujaMinima the pujaMinima to set
     */
    public void setPujaMinima(Double pujaMinima) {
        this.pujaMinima = pujaMinima;
    }

    /**
     * @return the fechaLiquidacion
     */
    public FechaValor getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    /**
     * @param fechaLiquidacion the fechaLiquidacion to set
     */
    public void setFechaLiquidacion(FechaValor fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    /**
     * @return the ventaEnCorto
     */
    public Boolean getVentaEnCorto() {
        return ventaEnCorto;
    }

    /**
     * @param ventaEnCorto the ventaEnCorto to set
     */
    public void setVentaEnCorto(Boolean ventaEnCorto) {
        this.ventaEnCorto = ventaEnCorto;
    }

    /**
     * @return the vectores
     */
    public List<VectorActivo> getVectores() {
        return vectores;
    }

    /**
     * @param vectores the vectores to set
     */
    public void setVectores(List<VectorActivo> vectores) {
        this.vectores = vectores;
    }

    /**
     * @return the propiedades
     */
    public List<ActivoPropiedadValor> getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(List<ActivoPropiedadValor> propiedades) {
        this.propiedades = propiedades;
    }

    /**
     * @return the tipo
     */
    public TipoActivo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoActivo tipo) {
        this.tipo = tipo;
    }
}
