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
@NamedQueries({
    @NamedQuery(name = "Transaction.BuyFromPortfolio", query = "SELECT t FROM Transaction t "
            + "WHERE t.transactionSource.name='Portfolio' AND t.name= 'Compra'"),
    @NamedQuery(name = "Transaction.SellFromPortfolio", query = "SELECT t FROM Transaction t "
            + "WHERE t.transactionSource.name='Portfolio' AND t.name= 'Venta'"),
    @NamedQuery(name = "Transaction.findByNameAndOwner", query = "SELECT t FROM Transaction t "
            + "WHERE t.transactionSource.name= :nameOwner AND t.name= :nameTransaction"),
    @NamedQuery(name = "Transaction.findByName", query = "SELECT t FROM Transaction t "
            + "WHERE t.name= :nameTransaction"),
    @NamedQuery(name = "Transaction.findByOperationAndOwner", query = "SELECT t "
            + "FROM Transaction t WHERE t.opCash= :cash "
            + "AND t.opQuantity= :quantity AND t.transactionSource.name= :source")
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
