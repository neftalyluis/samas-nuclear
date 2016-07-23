/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.Transaccion;
import mx.samas.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public Transaccion findByNombre(String nombre) {
        return transaccionRepository.getByNombre(nombre);
    }

}
