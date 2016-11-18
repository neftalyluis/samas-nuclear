/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service.elastic;

import mx.samas.domain.elastic.VectorActivoPropiedadValor;

/**
 *
 * @author samas
 */
public interface VectorActivoPropiedadValorService {

    void save(VectorActivoPropiedadValor article);

    VectorActivoPropiedadValor findOne(String id);

    Iterable<VectorActivoPropiedadValor> findAll();

    long count();

    void delete(VectorActivoPropiedadValor article);
}
