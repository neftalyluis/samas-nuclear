/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

    private String cuentaEje;

    @ManyToOne
    private Estrategia estrategia;

    @ManyToOne
    private TipoServicio tipoServicio;

    private LocalDate fecha;

    @ManyToOne
    private PortafolioEstatus estatus;

    @ManyToOne
    private Activo monedaDenominacion;

    @ManyToMany
    private List<Cliente> clientes;

    @ManyToMany
    private List<Cuenta> corredores;

    private Double margen;

    public Portafolio(String cuentaEje, Estrategia estrategia,
            TipoServicio tipoServicio, PortafolioEstatus estatus,
            Activo monedaDenominacion, List<Cliente> clientes,
            List<Cuenta> corredores, Double margen) {

        this.cuentaEje = cuentaEje;
        this.estrategia = estrategia;
        this.tipoServicio = tipoServicio;
        this.fecha = LocalDate.now();
        this.estatus = estatus;
        this.monedaDenominacion = monedaDenominacion;
        this.clientes = clientes;
        this.corredores = corredores;
        this.margen = margen;
    }

    public Portafolio() {
        this.fecha = LocalDate.now();
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
        return "mx.samas.domain.Portafolio[ id=" + id + " ]";
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

    /**
     * @return the estatus
     */
    public PortafolioEstatus getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(PortafolioEstatus estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the monedaDenominacion
     */
    public Activo getMonedaDenominacion() {
        return monedaDenominacion;
    }

    /**
     * @param monedaDenominacion the monedaDenominacion to set
     */
    public void setMonedaDenominacion(Activo monedaDenominacion) {
        this.monedaDenominacion = monedaDenominacion;
    }

    /**
     * @return the cuentaEje
     */
    public String getCuentaEje() {
        return cuentaEje;
    }

    /**
     * @param cuentaEje the cuentaEje to set
     */
    public void setCuentaEje(String cuentaEje) {
        this.cuentaEje = cuentaEje;
    }

    /**
     * @return the corredores
     */
    public List<Cuenta> getCorredores() {
        return corredores;
    }

    /**
     * @param corredores the corredores to set
     */
    public void setCorredores(List<Cuenta> corredores) {
        this.corredores = corredores;
    }

    /**
     * @return the clientes
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the margen
     */
    public Double getMargen() {
        return margen;
    }

    /**
     * @param margen the margen to set
     */
    public void setMargen(Double margen) {
        this.margen = margen;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
