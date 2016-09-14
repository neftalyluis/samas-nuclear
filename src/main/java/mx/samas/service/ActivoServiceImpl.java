/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedadValor;
import mx.samas.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class ActivoServiceImpl implements ActivoService {

    @Autowired
    private ActivoRepository activoRepository;

    @Override
    public List<Activo> getAllActivos() {
        return (List<Activo>) activoRepository.findAll();
    }

    @Override
    public Activo getById(Long id) {
        return (Activo) activoRepository.findOne(id);
    }

    @Override
    public List<Activo> getByNombre(String nombre) {
        return activoRepository.findByNombre(nombre);
    }

    @Override
    public Activo getByClavePizarra(String clavePizarra) {
        return activoRepository.findTopByClavePizarra(clavePizarra);
    }

    @Override
    public Activo createActivo(Activo input) {
        return activoRepository.save(input);
    }

    @Override
    public List<ActivoPropiedadValor> getPropiedadesFromActivo(Long id) {
        Activo a = getById(id);
        return a.getPropiedadesValor();
    }
}
