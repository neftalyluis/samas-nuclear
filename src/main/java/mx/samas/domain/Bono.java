/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author samas
 */
@Entity
public class Bono extends Activo implements Serializable {

    private Double diferencial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimiento;
    @OneToMany
    private List<FechaFlujo> fechasFlujo;
    @ManyToOne
    private TasaReferencia tasaReferencia;

    //Distintivas
    private Boolean llamable;
    private Boolean amortizable;
    @OneToMany
    private List<GradoCrediticio> grados;
    
    @Enumerated(EnumType.STRING)
    private AnoComercial anoComercial;

    public Bono() {

    }

    public Bono(String tipoValor, String emisora, String serie, String isin,
            String nombre, TipoActivo tipo, Boolean ventaEnCorto,
            Double pujaMinima) {
        super(tipoValor, emisora, serie, isin, nombre, tipo, ventaEnCorto, pujaMinima);

    }

    /**
     * @return the diferencial
     */
    public Double getDiferencial() {
        return diferencial;
    }

    /**
     * @param diferencial the diferencial to set
     */
    public void setDiferencial(Double diferencial) {
        this.diferencial = diferencial;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the fechasFlujo
     */
    public List<FechaFlujo> getFechasFlujo() {
        return fechasFlujo;
    }

    /**
     * @param fechasFlujo the fechasFlujo to set
     */
    public void setFechasFlujo(List<FechaFlujo> fechasFlujo) {
        this.fechasFlujo = fechasFlujo;
    }

    /**
     * @return the llamable
     */
    public Boolean getLlamable() {
        return llamable;
    }

    /**
     * @param llamable the llamable to set
     */
    public void setLlamable(Boolean llamable) {
        this.llamable = llamable;
    }

    /**
     * @return the amortizable
     */
    public Boolean getAmortizable() {
        return amortizable;
    }

    /**
     * @param amortizable the amortizable to set
     */
    public void setAmortizable(Boolean amortizable) {
        this.amortizable = amortizable;
    }

    /**
     * @return the tasaReferencia
     */
    public TasaReferencia getTasaReferencia() {
        return tasaReferencia;
    }

    /**
     * @param tasaReferencia the tasaReferencia to set
     */
    public void setTasaReferencia(TasaReferencia tasaReferencia) {
        this.tasaReferencia = tasaReferencia;
    }

    /**
     * @return the grados
     */
    public List<GradoCrediticio> getGrados() {
        return grados;
    }

    /**
     * @param grados the grados to set
     */
    public void setGrados(List<GradoCrediticio> grados) {
        this.grados = grados;
    }

    /**
     * @return the anoComercial
     */
    public AnoComercial getAnoComercial() {
        return anoComercial;
    }

    /**
     * @param anoComercial the anoComercial to set
     */
    public void setAnoComercial(AnoComercial anoComercial) {
        this.anoComercial = anoComercial;
    }
}
