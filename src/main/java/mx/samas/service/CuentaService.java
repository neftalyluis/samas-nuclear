/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.Cuenta;

/**
 *
 * @author samas
 */
public interface CuentaService {

    public Cuenta getCuentaByCadena(String cadena);

    public Cuenta createOrUpdateCuenta(Cuenta c);
}
