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
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
public class Portafolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Estrategia estrategia;
    
    @ManyToOne
    private TipoServicio tipoServicio;

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
        if (!(object instanceof Portafolio)) {
            return false;
        }
        Portafolio other = (Portafolio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.newdomain.Portafolio[ id=" + id + " ]";
    }

    /**
     * @return the estrategia
     */
    public Estrategia getEstrategia() {
        return estrategia;
    }

    /**
     * @param estrategia the estrategia to set
     */
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * @return the tipoServicio
     */
    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    /**
     * @param tipoServicio the tipoServicio to set
     */
    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

}
