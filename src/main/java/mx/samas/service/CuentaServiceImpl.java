/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.LinkedList;
import java.util.List;
import mx.samas.domain.Banco;
import mx.samas.domain.CuentaCorredor;
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
    public CuentaCorredor getByIdCuenta(String cadena) {
        return cuentaRepository.getByIdCuenta(cadena);
    }

    @Override
    public CuentaCorredor createOrUpdateCuenta(CuentaCorredor c) {
        return cuentaRepository.save(c);
    }

    @Override
    public CuentaCorredor createFromDto(CuentaDTO c) {
        Banco b = bancoService.getBancoById(c.getBancoId());

        CuentaCorredor entity = new CuentaCorredor(c.getIdCuenta(), b, c.getTieneCredito(),
                c.getOperaFlujo(), c.getOperaIndice(), c.getOperaDerivado());

        return cuentaRepository.save(entity);
    }

    @Override
    public List<CuentaCorredor> createFromDto(List<CuentaDTO> cuentaList) {
        List<CuentaCorredor> createdCuentas = new LinkedList<>();
        for (CuentaDTO cuenta : cuentaList) {
            createdCuentas.add(createFromDto(cuenta));
        }
        return createdCuentas;
    }

    @Override
    public List<CuentaCorredor> getAll() {
        return cuentaRepository.findAll();
    }

    @Override
    public CuentaCorredor getById(Long id) {
        return cuentaRepository.findOne(id);
    }

    @Override
    public Boolean removeById(Long id) {
        cuentaRepository.delete(id);
        return true;
    }

    @Override
    public List<String> getAllIdCuenta() {
        return cuentaRepository.getAllIdCuenta();
    }

}
