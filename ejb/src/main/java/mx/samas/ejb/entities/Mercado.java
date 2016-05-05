/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 */
@Entity
@XmlRootElement
public class Mercado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @OneToOne
    private DenominacionMoneda moneda;

    @ManyToMany
    private List<TipoValor> tipoValor;

    @ManyToMany
    private List<Corredor> corredores;

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
        if (!(object instanceof Mercado)) {
            return false;
        }
        Mercado other = (Mercado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Market[ id=" + id + " ]";
    }


    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the moneda
     */
    public DenominacionMoneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(DenominacionMoneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the tipoValor
     */
    public List<TipoValor> getTipoValor() {
        return tipoValor;
    }

    /**
     * @param tipoValor the tipoValor to set
     */
    public void setTipoValor(List<TipoValor> tipoValor) {
        this.tipoValor = tipoValor;
    }

    /**
     * @return the corredores
     */
    public List<Corredor> getCorredores() {
        return corredores;
    }

    /**
     * @param corredores the corredores to set
     */
    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }

}
