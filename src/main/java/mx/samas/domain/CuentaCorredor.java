/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author samas
 */
@Entity
public class CuentaCorredor extends Cuenta implements Serializable {

    
    /**
     * Tiene Credito implica que todo el Contrato esta prendado.
     *
     * Sacar dinero implica una autorizacion, para evitar llamadas de Margen.
     */
    private Boolean tieneCredito;
    private Boolean operaFlujo;
    private Boolean operaIndice;
    private Boolean operaDerivado;

    public CuentaCorredor() {
    }

    /**
     * @param idCuenta Guardara la cadena que se ingrese desde la interfaz.
     * @param banco Guardara el banco que se ingrese desde la interfaz.
     * @param tieneCredito Solo acepta como valores verdadero o falso (booleano)
     * el cual guardara.
     * @param operaFlujo Solo acepta como valores verdadero o falso (booleano)
     * el cual guardara.
     * @param operaIndice Solo acepta como valores verdadero o falso (booleano)
     * el cual guardara.
     * @param operaDerivado Solo acepta como valores verdadero o falso
     * (booleano) el cual guardara.
     */
    public CuentaCorredor(String idCuenta, Banco banco, Boolean tieneCredito,
            Boolean operaFlujo, Boolean operaIndice, Boolean operaDerivado) {
        this.tieneCredito = tieneCredito;
        this.operaFlujo = operaFlujo;
        this.operaIndice = operaIndice;
        this.operaDerivado = operaDerivado;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Cuenta[ id=" + this.getIdCuenta() + " ]";
    }

    /**
     * @return the operaFlujo
     */
    public Boolean getOperaFlujo() {
        return operaFlujo;
    }

    /**
     * @param operaFlujo the operaFlujo to set
     */
    public void setOperaFlujo(Boolean operaFlujo) {
        this.operaFlujo = operaFlujo;
    }

    /**
     * @return the operaIndice
     */
    public Boolean getOperaIndice() {
        return operaIndice;
    }

    /**
     * @param operaIndice the operaIndice to set
     */
    public void setOperaIndice(Boolean operaIndice) {
        this.operaIndice = operaIndice;
    }

    /**
     * @return the operaDerivado
     */
    public Boolean getOperaDerivado() {
        return operaDerivado;
    }

    /**
     * @param operaDerivado the operaDerivado to set
     */
    public void setOperaDerivado(Boolean operaDerivado) {
        this.operaDerivado = operaDerivado;
    }

    /**
     * @return the tieneCredito
     */
    public Boolean getTieneCredito() {
        return tieneCredito;
    }

    /**
     * @param tieneCredito the tieneCredito to set
     */
    public void setTieneCredito(Boolean tieneCredito) {
        this.tieneCredito = tieneCredito;
    }

}
