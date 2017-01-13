/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Entity
public class ActivoPropiedadValor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String valor;

    @ManyToOne
    private ActivoPropiedad propiedad;

    public ActivoPropiedadValor() {

    }

    /**
     * @param valor Guardara la cadena que se ingrese desde la interfaz.
     * @param propiedad Guardara el ActivoPropiedad que se ingrese desde la interfaz.
     */
    public ActivoPropiedadValor(String valor, ActivoPropiedad propiedad) {
        this.valor = valor;
        this.propiedad = propiedad;
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
        if (!(object instanceof ActivoPropiedadValor)) {
            return false;
        }
        ActivoPropiedadValor other = (ActivoPropiedadValor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.ActivoPropiedadValor[ id=" + id + " ]";
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the propiedad
     */
    public ActivoPropiedad getPropiedad() {
        return propiedad;
    }

    /**
     * @param propiedad the propiedad to set
     */
    public void setPropiedad(ActivoPropiedad propiedad) {
        this.propiedad = propiedad;
    }

}
