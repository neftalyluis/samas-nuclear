/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Quien dirige la transaccion
     */
    @ManyToOne
    private DuenoFuente fuenteTransaccion;

    /**
     * Nombre de la Transaccion
     */
    @Column(unique = true, nullable = false)
    private String nombre;

    /**
     * Si esta transaccion es a credito
     */
    private Boolean credito;

    /**
     * Indica si entran, salen o no hay flujo de titulos
     */
    private Long flujoTitulos;

    /**
     * Indica si entran, salen o no hay flujo de efectivo
     */
    private Long flujoEfectivo;
    
    /**
     * Relacion que indica que comisiones aplican para esta transaccion
     */
    @OneToMany
    private List<TransaccionComision> comisiones;
    
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
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Transaccion[ id=" + id + " ]";
    }

    /**
     * @return the fuenteTransaccion
     */
    public DuenoFuente getFuenteTransaccion() {
        return fuenteTransaccion;
    }

    /**
     * @param fuenteTransaccion the fuenteTransaccion to set
     */
    public void setFuenteTransaccion(DuenoFuente fuenteTransaccion) {
        this.fuenteTransaccion = fuenteTransaccion;
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
     * @return the credito
     */
    public Boolean getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(Boolean credito) {
        this.credito = credito;
    }

    /**
     * @return the flujoTitulos
     */
    public Long getFlujoTitulos() {
        return flujoTitulos;
    }

    /**
     * @param flujoTitulos the flujoTitulos to set
     */
    public void setFlujoTitulos(Long flujoTitulos) {
        this.flujoTitulos = flujoTitulos;
    }

    /**
     * @return the flujoEfectivo
     */
    public Long getFlujoEfectivo() {
        return flujoEfectivo;
    }

    /**
     * @param flujoEfectivo the flujoEfectivo to set
     */
    public void setFlujoEfectivo(Long flujoEfectivo) {
        this.flujoEfectivo = flujoEfectivo;
    }

    /**
     * @return the comisiones
     */
    public List<TransaccionComision> getComisiones() {
        return comisiones;
    }

    /**
     * @param comisiones the comisiones to set
     */
    public void setComisiones(List<TransaccionComision> comisiones) {
        this.comisiones = comisiones;
    }

}
