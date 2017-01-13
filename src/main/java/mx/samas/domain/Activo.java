/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
@NamedQuery(name = "Activo.existWithClavePizarra",
        query = "SELECT CASE WHEN (count(act) > 0) THEN TRUE ELSE FALSE END "
        + "FROM Activo act WHERE act.clavePizarra = :clavePizarra")
public class Activo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nombre del Activo
     */
    private String nombre;

    /**
     * Identificador de un activo, es la concatenacion de TV, Emisora y Serie
     */
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
    private List<VectorActivo> vectores;

    /**
     * Tipo de activo:
     *
     * -Equity -Bond -Currency -Derivative
     */
    @Enumerated(EnumType.ORDINAL)
    private TipoActivo tipo;

    @OneToMany
    private List<ActivoPropiedadValor> propiedadesValor;

    @ManyToMany
    private List<ActivoPropiedad> propiedades;

    @ManyToOne
    private Mercado mercado;

    public Activo() {

    }

    /**
     * @param tipoValor Guardara la cadena que se ingrese desde la interfaz.
     * @param emisora Guardara la cadena que se ingrese desde la interfaz.
     * @param serie Guardara la cadena que se ingrese desde la interfaz.
     * @param isin Guardara la cadena que se ingrese desde la interfaz.
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     * @param tipo Guardara el TipoActivo que se seleccione desde la interfaz.
     * @param ventaEnCorto Solo acepta como valores verdadero o falso (booleano) el cual guardara.
     * @param pujaMinima Guardara un numero fraccionario (de hasta 15 digitos) que se ingrese desde la interfaz. 
     */
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
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     * @param tipoValor Guardara la cadena que se ingrese desde la interfaz.
     * @param emisora Guardara la cadena que se ingrese desde la interfaz.
     * @param serie Guardara la cadena que se ingrese desde la interfaz.
     */
    public Activo(String nombre, String tipoValor, String emisora, String serie) {
        this.clavePizarra = tipoValor + "_" + emisora + "_" + serie;
        this.tipoValor = tipoValor;
        this.emisora = emisora;
        this.serie = serie;
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
        return "mx.samas.domain.Activo[ id=" + id + " ]";
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
     * @return the propiedadesValor
     */
    public List<ActivoPropiedadValor> getPropiedadesValor() {
        return propiedadesValor;
    }

    /**
     * @param propiedadesValor the propiedadesValor to set
     */
    public void setPropiedadesValor(List<ActivoPropiedadValor> propiedadesValor) {
        this.propiedadesValor = propiedadesValor;
    }

    /**
     * @return the propiedades
     */
    public List<ActivoPropiedad> getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(List<ActivoPropiedad> propiedades) {
        this.propiedades = propiedades;
    }

    /**
     * @return the mercado
     */
    public Mercado getMercado() {
        return mercado;
    }

    /**
     * @param mercado the mercado to set
     */
    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

}
