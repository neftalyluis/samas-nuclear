/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta Entidad representa el nodo de un arbol binario de grupos de propiedades
 * de los Activos
 *
 * @author samas
 */
@Entity
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long piso;
    private Long techo;
    private Long grupoPadre;

    @ElementCollection
    private List<GrupoPropiedad> propiedades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Grupo[ id=" + getId() + " ]";
    }

    /**
     * @return the piso
     */
    public Long getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(Long piso) {
        this.piso = piso;
    }

    /**
     * @return the techo
     */
    public Long getTecho() {
        return techo;
    }

    /**
     * @param techo the techo to set
     */
    public void setTecho(Long techo) {
        this.techo = techo;
    }

    /**
     * @return the grupoPadre
     */
    public Long getGrupoPadre() {
        return grupoPadre;
    }

    /**
     * @param grupoPadre the grupoPadre to set
     */
    public void setGrupoPadre(Long grupoPadre) {
        this.grupoPadre = grupoPadre;
    }

    /**
     * @return the propiedades
     */
    public List<GrupoPropiedad> getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(List<GrupoPropiedad> propiedades) {
        this.propiedades = propiedades;
    }

}
