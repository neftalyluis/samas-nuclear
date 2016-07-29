/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.Date;
import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.Bitacora;
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
    public List<Bitacora> getBitacoraListWithOperationDate(Date operationDate) {
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

}