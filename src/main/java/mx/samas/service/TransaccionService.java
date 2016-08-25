/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Transaccion;

/**
 *
 * @author samas
 */
public interface TransaccionService {

    public Transaccion findByNombre(String nombre);

    public void createTransaccionesFromList(List<Transaccion> transacciones);

    public Transaccion findById(Long id);
}
