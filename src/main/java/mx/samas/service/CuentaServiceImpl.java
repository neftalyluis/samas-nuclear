/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.LinkedList;
import java.util.List;
import mx.samas.domain.Banco;
import mx.samas.domain.Cuenta;
import mx.samas.domain.dto.CuentaDTO;
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

    @Autowired
    private BancoService bancoService;

    @Override
    public Cuenta getCuentaByCadena(String cadena) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Cuenta createOrUpdateCuenta(Cuenta c) {
        return cuentaRepository.save(c);
    }

    @Override
    public Cuenta createFromDto(CuentaDTO c) {
        Banco b = bancoService.getBancoById(c.getBancoId());

        Cuenta entity = new Cuenta(c.getIdCuenta(), b, c.getTieneCredito(),
                c.getOperaFlujo(), c.getOperaIndice(), c.getOperaDerivado());
        
        return cuentaRepository.save(entity);
    }

    @Override
    public List<Cuenta> createFromDto(List<CuentaDTO> cuentaList) {
        List<Cuenta> createdCuentas = new LinkedList<>();
        for(CuentaDTO cuenta: cuentaList){
            createdCuentas.add(createFromDto(cuenta));
        }
        return createdCuentas;
    }

}
