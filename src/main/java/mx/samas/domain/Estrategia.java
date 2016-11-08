/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
public class Estrategia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "estrategia", cascade = CascadeType.ALL)
    private List<VectorPortafolioModelo> estrategiaModelo;

    private Boolean liquidez;
    private Boolean efectivo;
    private Boolean grupos;
    private Boolean margen;
    
    @ManyToMany
    private List<Grupo> grupoLista;
    

    //    @ManyToOne
//    private PerfilRiesgo perfilRiesgo;
    public Estrategia() {

    }

    public Estrategia(String nombre, List<VectorPortafolioModelo> modelo) {
        this.nombre = nombre;
        this.estrategiaModelo = modelo;

    }

    public Estrategia(String nombre) {
        this.nombre = nombre;

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
        return "mx.samas.domain.Estrategia[ id=" + id + " ]";
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
     * @return the liquidez
     */
    public Boolean getLiquidez() {
        return liquidez;
    }

    /**
     * @param liquidez the liquidez to set
     */
    public void setLiquidez(Boolean liquidez) {
        this.liquidez = liquidez;
    }

    /**
     * @return the efectivo
     */
    public Boolean getEfectivo() {
        return efectivo;
    }

    /**
     * @param efectivo the efectivo to set
     */
    public void setEfectivo(Boolean efectivo) {
        this.efectivo = efectivo;
    }

    /**
     * @return the grupos
     */
    public Boolean getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(Boolean grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the margen
     */
    public Boolean getMargen() {
        return margen;
    }

    /**
     * @param margen the margen to set
     */
    public void setMargen(Boolean margen) {
        this.margen = margen;
    }

    /**
     * @return the grupoLista
     */
    public List<Grupo> getGrupoLista() {
        return grupoLista;
    }

    /**
     * @param grupoLista the grupoLista to set
     */
    public void setGrupoLista(List<Grupo> grupoLista) {
        this.grupoLista = grupoLista;
    }

}
