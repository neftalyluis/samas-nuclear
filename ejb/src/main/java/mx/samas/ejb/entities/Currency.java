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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Esta entidad que extiende a Asset sirve para distinguir los Activos que son
 * moneda, no usa otros parametros mas alla de los suministrados por la clase
 * padre.
 */
@Entity
@XmlRootElement
public class Currency extends Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nuevo objeto vacio
     */
    public Currency() {
    }

    /**
     * @param name El nombre del Activo
     * @param securityClass El tipo valor
     * @param issuer La emisora
     * @param series La serie
     * @param shortSale ¿Este activo se puede usar para ventas en corto?
     */
    public Currency(String name, String securityClass, Issuer issuer, String series, Boolean shortSale) {
        super(name, securityClass, issuer, series, shortSale);
        
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
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Currency[ id=" + id + " ]";
    }

}
