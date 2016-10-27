/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class DevengoServiceImpl implements DevengoService {

    @Override
    public Double calculate(Double tipoDeCambio, Long posicion, Double unitario, Double tasa, Double dias) {
        return tipoDeCambio * posicion * unitario * tasa * dias;
    }

    
}
