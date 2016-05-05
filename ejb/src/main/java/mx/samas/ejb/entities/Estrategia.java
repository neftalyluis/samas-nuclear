/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Esta entidad se encarga de agrupar los objetivos de uno o mas portafolios: -
 * Lista de Activos - Perfil de Riesgo (TBD)
 *
 *
 *
 * Boolean ShortSale
 *
 * @author alfonso
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Strategy.findByName", query = "SELECT s FROM Strategy s WHERE s.name = :name"),
    @NamedQuery(name = "Strategy.findByID", query = "SELECT s FROM Strategy s WHERE s.id = :id")

})
public class Estrategia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "strategy", cascade = CascadeType.ALL)
    private List<VectorPortafolioModelo> estrategiaModelo;

    @ManyToOne
    private PerfilRiesgo perfilRiesgo;

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
        if (!(object instanceof Estrategia)) {
            return false;
        }
        Estrategia other = (Estrategia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.Strategy[ id=" + id + " ]";
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
     * @return the estrategiaModelo
     */
    public List<VectorPortafolioModelo> getEstrategiaModelo() {
        return estrategiaModelo;
    }

    /**
     * @param estrategiaModelo the estrategiaModelo to set
     */
    public void setEstrategiaModelo(List<VectorPortafolioModelo> estrategiaModelo) {
        this.estrategiaModelo = estrategiaModelo;
    }

    /**
     * @return the perfilRiesgo
     */
    public PerfilRiesgo getPerfilRiesgo() {
        return perfilRiesgo;
    }

    /**
     * @param perfilRiesgo the perfilRiesgo to set
     */
    public void setPerfilRiesgo(PerfilRiesgo perfilRiesgo) {
        this.perfilRiesgo = perfilRiesgo;
    }

}
