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
public class Moneda extends Activo implements Serializable {

    public Moneda() {

    }

    public Moneda(String tipoValor, String emisora, String serie, String isin,
            String nombre, TipoActivo tipo, Boolean ventaEnCorto,
            Double pujaMinima) {
        super(tipoValor, emisora, serie, isin, nombre, tipo, ventaEnCorto, pujaMinima);

    }

}
