/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.repository.ActivoPropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class ActivoPropiedadServiceImpl implements ActivoPropiedadService {

    @Autowired
    private ActivoPropiedadRepository activoPropiedadRepository;

    @Override
    public List<ActivoPropiedad> getPropiedades() {
        return (List<ActivoPropiedad>) activoPropiedadRepository.findAll();
    }

    @Override
    public ActivoPropiedad getPropiedadWithNombre(String nombre) {
        return activoPropiedadRepository.findByNombre(nombre);
    }

    @Override
    public ActivoPropiedad getPropiedadWithId(Long id) {
        return activoPropiedadRepository.findOne(id);
    }

    @Override
    public ActivoPropiedad createPropiedad(ActivoPropiedad a) {
        return activoPropiedadRepository.save(a);
    }

}
