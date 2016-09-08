/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.Cuenta;
import mx.samas.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta getCuentaByCadena(String cadena) {
        return cuentaRepository.getCuentaByCadena(cadena);
    }

    @Override
    public Cuenta createOrUpdateCuenta(Cuenta c) {
        return cuentaRepository.save(c);
    }

}
