/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.Bitacora;
import mx.samas.domain.CuentaCorredor;
import mx.samas.domain.Portafolio;
import mx.samas.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class BitacoraServiceImpl implements BitacoraService {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Autowired
    private ActivoService activoService;

    @Override
    public List<Bitacora> getBitacoraListWithOperationDate(LocalDate operationDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bitacora> getBitacoraListWithContractNumber(Long contratoNumero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bitacora> getBitacoraListForActivoWithClavePizarra(String clavePizarra) {
        Activo a = activoService.getByClavePizarra(clavePizarra);
        return bitacoraRepository.findByActivo(a);
    }

    @Override
    public Bitacora saveBitacoraEntry(Bitacora entry) {
        return bitacoraRepository.save(entry);
    }

    @Override
    public List<Bitacora> saveBitacoraEntries(List<Bitacora> bitacoraList) {
        return (List<Bitacora>) bitacoraRepository.save(bitacoraList);
    }

    @Override
    public List<Bitacora> getBitacoraListFromLastDay(CuentaCorredor item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bitacora> getBitacoraListFromLastDay(Portafolio item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bitacora> getAllEntries() {
        return bitacoraRepository.findAll();
    }

}
