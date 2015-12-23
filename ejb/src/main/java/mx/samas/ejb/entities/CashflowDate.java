/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author neftaly
 */
@Entity
public class CashflowDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset bond;
    /* Retos:  
     (1) ¿Cómo le vamos a hacer con los perpetuos?
     los perpetuos -- sin venciminto -- se valúan con una función de límite 
     donde el resultado es:
        
     Precio = Flujo/tasa
                  
     por ende, cuándo son perpetuos, solo es necesario capturar spreadFixed 
     y la fecha de pagos
    
     (2) ¿Cómo vamos a determinar los cashflowDates para los revisables?
     Para la siguiente fecha de pago, sabemos la tasa cupón -- ya que se
     fijó en la última revisión y se mantiene constante hasta las siguiente
     revisión. Los cashflowDates subsecuentes, se calculan de manera uniforme
     suponiendo que marketSpread se mantiene constante; sin embargo, cada que
     haya un cambio en marketSpread, estos cambian. marketSpread cambia por
     (1) valuación (una vez al día) o (2) para operar en secundario.
    
     */

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date payDate;
    /**
     * 1- Interest 0.- Principal
     */
    private Boolean interest;

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
        if (!(object instanceof CashflowDate)) {
            return false;
        }
        CashflowDate other = (CashflowDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.CouponDate[ id=" + id + " ]";
    }

    /**
     * @return the payDate
     */
    public Date getPayDay() {
        return payDate;
    }

    /**
     * @param payDay the payDate to set
     */
    public void setPayDay(Date payDay) {
        this.payDate = payDay;
    }

    /**
     * @return the interest
     */
    public Boolean getCouponPrincipal() {
        return interest;
    }

    /**
     * @param couponPrincipal the interest to set
     */
    public void setCouponPrincipal(Boolean couponPrincipal) {
        this.interest = couponPrincipal;
    }

    /**
     * @return the bond
     */
    public Asset getBond() {
        return bond;
    }

    /**
     * @param bond the bond to set
     */
    public void setBond(Asset bond) {
        this.bond = bond;
    }

}
