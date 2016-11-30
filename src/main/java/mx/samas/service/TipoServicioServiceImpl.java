/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.TipoServicio;
import mx.samas.repository.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;
    
    @Override
    public TipoServicio getTipoServicioById(Long id) {
        return tipoServicioRepository.findOne(id);
    }

}
