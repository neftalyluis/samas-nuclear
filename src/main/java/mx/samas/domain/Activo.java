/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import mx.samas.domain.dto.ActivoPropiedadValor;

/**
 *
 * @author samas
 */
@Entity
public class Activo extends ParentModel {

    /**
     * Nombre del Activo
     */
    private String nombre;

    /**
     * Identificador de un activo, es la concatenacion de TV, Emisora y Serie
     */
    @NotNull
    @Column(unique = true)
    private String clavePizarra;

    /**
     * Tipo Valor
     */
    private String tipoValor;

    /**
     * Emisora
     */
    private String emisora;

    /**
     * Serie
     */
    private String serie;

    /**
     * Identificador unico de un Activo, puede ser otra opcioón ademas del
     * clavePizarra
     */
    @Column(unique = true)
    private String isin;

    /**
     * "pujaMinima" es lo que viene siendo la "puja"
     *
     * Unidad para ofertar
     *
     * Puja minima,
     */
    private Double pujaMinima;

    /**
     * Este valor indica si se puede usar este Activo para ventas en corto
     */
    private Boolean ventaEnCorto;

    @JsonIgnore
    @OneToMany(mappedBy = "activo", cascade = CascadeType.ALL)
//    @OneToMany
    private List<VectorActivo> vectores;

    /**
     * Tipo de activo:
     *
     * -Equity -Bond -Currency -Derivative
     */
    @Enumerated(EnumType.ORDINAL)
    private TipoActivo tipo;
    
    @Lob
    private List<ActivoPropiedadValor> propiedades;

    public Activo() {

    }

    public Activo(String tipoValor, String emisora, String serie, String isin,
            String nombre, TipoActivo tipo, Boolean ventaEnCorto,
            Double pujaMinima) {

        this.clavePizarra = tipoValor + "_" + emisora + "_" + serie;
        this.tipoValor = tipoValor;
        this.emisora = emisora;
        this.serie = serie;
        this.isin = isin;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ventaEnCorto = ventaEnCorto;
        this.pujaMinima = pujaMinima;

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

    /**
     * @return the emisora
     */
    public String getEmisora() {
        return emisora;
    }

    /**
     * @param emisora the emisora to set
     */
    public void setEmisora(String emisora) {
        this.emisora = emisora;
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

}
