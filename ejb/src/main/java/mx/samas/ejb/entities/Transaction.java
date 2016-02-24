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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 */
@Entity
@XmlRootElement
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Quien dirige la transaccion
     */
    @ManyToOne
    private SourceOwner transactionSource;

    /**
     * Nombre de la Transaccion
     */
    private String name;

    /**
     * Si esta transaccion es a credito
     */
    private Boolean credit;

    /**
     * Indica si entran, salen o no hay flujo de titulos
     */
    private Long opQuantity;

    /**
     * Indica si entran, salen o no hay flujo de efectivo
     */
    private Long opCash;

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
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
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
     * @return the opQuantity
     */
    public Long getOpQuantity() {
        return opQuantity;
    }

    /**
     * @param opQuantity the opQuantity to set
     */
    public void setOpQuantity(Long opQuantity) {
        this.opQuantity = opQuantity;
    }

    /**
     * @return the opCash
     */
    public Long getOpCash() {
        return opCash;
    }

    /**
     * @param opCash the opCash to set
     */
    public void setOpCash(Long opCash) {
        this.opCash = opCash;
    }

    /**
     * @return the credit
     */
    public Boolean getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(Boolean credit) {
        this.credit = credit;
    }

    /**
     * @return the transactionSource
     */
    public SourceOwner getTransactionSource() {
        return transactionSource;
    }

    /**
     * @param transactionSource the transactionSource to set
     */
    public void setTransactionSource(SourceOwner transactionSource) {
        this.transactionSource = transactionSource;
    }

}
