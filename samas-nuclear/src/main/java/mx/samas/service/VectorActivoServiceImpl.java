/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.VectorActivo;
import mx.samas.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class VectorActivoServiceImpl implements VectorActivoService {

    @Autowired
    private ActivoRepository activoRepository;

    @Override
    public List<VectorActivo> getVectorFromActivo(Long id) {
        Activo u = activoRepository.findOne(id);
        return u.getVectores();
    }

    @Override
    public List<VectorActivo> getVectorFromActivo(String clavePizarra) {
        Activo u = activoRepository.findFirstByClavePizarra(clavePizarra);
        return u.getVectores();
    }

}