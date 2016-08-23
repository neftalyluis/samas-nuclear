/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.DuenoFuente;
import mx.samas.repository.DuenoFuenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class DuenoFuenteServiceImpl implements DuenoFuenteService {

    @Autowired
    private DuenoFuenteRepository duenoFuenteRepository;

    @Override
    public void createDuenoFuente(DuenoFuente source) {
        duenoFuenteRepository.save(source);
    }

    @Override
    public DuenoFuente findDuenoFuenteByName(String name) {
        return duenoFuenteRepository.findByNombre(name);
    }

}
