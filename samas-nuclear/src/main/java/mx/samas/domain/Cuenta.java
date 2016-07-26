/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
public class Cuenta extends ParentModel {

    @ManyToMany
    private List<Cliente> titular;
    private String cadena;
    @ManyToOne
    private Banco banco;

    @OneToMany
    private List<Portafolio> portafolios;

    /**
     * @return the titular
     */
    public List<Cliente> getTitular() {
        return titular;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(List<Cliente> titular) {
        this.titular = titular;
    }

    /**
     * @return the cadena
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * @param cadena the cadena to set
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
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

    /**
     * @return the portafolios
     */
    public List<Portafolio> getPortafolios() {
        return portafolios;
    }

    /**
     * @param portafolios the portafolios to set
     */
    public void setPortafolios(List<Portafolio> portafolios) {
        this.portafolios = portafolios;
    }
}
