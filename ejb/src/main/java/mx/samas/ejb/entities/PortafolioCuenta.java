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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Si es discretionary, entonces activeCommission es una comision devengada,
 * sino es transaccional
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortfolioAccount.findByAccountNumber", query = "SELECT pa FROM PortfolioAccount pa WHERE pa.accountNumber= :account")
})
public class PortafolioCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numero de cuenta
     */
    private String numeroCuenta;

    /**
     * Se refiere a que si esta contrato tiene comision discrecional
     */
    private Boolean comisionDiscrecional;

    /**
     * La comision activa
     */
    private Double comisionActiva;

    /**
     * Indica si esta cuenta acepta ventas en corto
     */
    private Boolean ventaCorto;

    /**
     * El banco al cual pertenece esta cuenta
     */
    @ManyToOne
    private Banco banco;

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
        if (!(object instanceof PortafolioCuenta)) {
            return false;
        }
        PortafolioCuenta other = (PortafolioCuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Contract[ id=" + id + " ]";
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the comisionDiscrecional
     */
    public Boolean getComisionDiscrecional() {
        return comisionDiscrecional;
    }

    /**
     * @param comisionDiscrecional the comisionDiscrecional to set
     */
    public void setComisionDiscrecional(Boolean comisionDiscrecional) {
        this.comisionDiscrecional = comisionDiscrecional;
    }

    /**
     * @return the comisionActiva
     */
    public Double getComisionActiva() {
        return comisionActiva;
    }

    /**
     * @param comisionActiva the comisionActiva to set
     */
    public void setComisionActiva(Double comisionActiva) {
        this.comisionActiva = comisionActiva;
    }

    /**
     * @return the ventaCorto
     */
    public Boolean getVentaCorto() {
        return ventaCorto;
    }

    /**
     * @param ventaCorto the ventaCorto to set
     */
    public void setVentaCorto(Boolean ventaCorto) {
        this.ventaCorto = ventaCorto;
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

}
