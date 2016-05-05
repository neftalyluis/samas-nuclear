/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Esta entidad refleja el comportamiento sobre el tiempo de portafolios, dicese
 * comportamiendo al cambio de Estrategia, Clientes, Estatus o Cuenta.
 *
 * 
 * Nota Adicional : 
 * 
 * Un cliente puede tener muchos portafolios en un contrato y que el pago de
 * dividendo/cupon/rendimiento se asigna a cada portafolio en funcion de los
 * titulos que tiene Si es discretionary, entonces comission es una comision
 * devengada, sino es transaccional
 *
 * @author neftaly
 */
@Entity
@XmlRootElement
public class VectorPortafolio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "portfolios")
    private List<Cliente> clientes;

    @ManyToOne
    private Estrategia estrategia;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    private PortafolioCuenta cuenta;

    @ManyToOne
    private PortafolioEstatus estatus;

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
        if (!(object instanceof VectorPortafolio)) {
            return false;
        }
        VectorPortafolio other = (VectorPortafolio) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Portfolio[ id=" + getId() + " ]";
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the cuenta
     */
    public PortafolioCuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(PortafolioCuenta cuenta) {
        this.cuenta = cuenta;
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

}
