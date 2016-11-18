/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service.elastic;

import mx.samas.domain.elastic.VectorActivoPropiedadValor;
import mx.samas.repository.elastic.VectorActivoPropiedadValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class VectorActivoPropiedadValorServiceImpl implements VectorActivoPropiedadValorService {

    @Autowired
    private VectorActivoPropiedadValorRepository vectorRepository;

    @Override
    public void save(VectorActivoPropiedadValor article) {
        vectorRepository.save(article);
    }

    @Override
    public VectorActivoPropiedadValor findOne(String id) {
        return vectorRepository.findOne(id);
    }

    @Override
    public Iterable<VectorActivoPropiedadValor> findAll() {
        return vectorRepository.findAll();
    }

    @Override
    public long count() {
        return vectorRepository.count();
    }

    @Override
    public void delete(VectorActivoPropiedadValor article) {
        vectorRepository.delete(article);
    }

}
