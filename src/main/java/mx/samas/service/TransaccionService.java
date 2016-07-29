/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.Transaccion;

/**
 *
 * @author samas
 */
public interface TransaccionService {

    public Transaccion findByNombre(String nombre);
}
