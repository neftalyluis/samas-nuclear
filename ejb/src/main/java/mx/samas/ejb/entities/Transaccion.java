/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 */
@Entity
@XmlRootElement
//TBD
@NamedQueries({
    @NamedQuery(name = "Transaccion.comprasDesdePortafolio", query = "SELECT t FROM Transaccion t "
            + "WHERE t.fuenteTransaccion.nombre='Portfolio' AND t.nombre= 'Compra'"),
    @NamedQuery(name = "Transaccion.ventasDesdePortafolio", query = "SELECT t FROM Transaccion t "
            + "WHERE t.fuenteTransaccion.nombre='Portfolio' AND t.nombre= 'Venta'"),
    @NamedQuery(name = "Transaccion.buscarPorNombreyDueno", query = "SELECT t FROM Transaccion t "
            + "WHERE t.fuenteTransaccion.nombre= :nombreDueno AND t.nombre= :nombreTransaccion"),
    @NamedQuery(name = "Transaccion.buscarPorNombre", query = "SELECT t FROM Transaccion t "
            + "WHERE t.nombre= :nombre"),
    @NamedQuery(name = "Transaccion.buscarPorFlujosyDueno", query = "SELECT t "
            + "FROM Transaccion t WHERE t.flujoEfectivo= :efectivo "
            + "AND t.flujoTitulos= :titulos AND t.fuenteTransaccion.nombre= :nombre")
})
public class Transaccion implements Serializable {

    public Transaccion() {

    }

    public Transaccion(DuenoFuente owner, String nombre, Boolean isCredit, Long quantity, Long cash) {
        this.credito = isCredit;
        this.nombre = nombre;
        this.flujoEfectivo = cash;
        this.flujoTitulos = quantity;
        this.fuenteTransaccion = owner;

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Quien dirige la transaccion
     */
    @ManyToOne
    private DuenoFuente fuenteTransaccion;

    /**
     * Nombre de la Transaccion
     */
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
        return "mx.samas.entities.Transaction[ id=" + id + " ]";
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

}
