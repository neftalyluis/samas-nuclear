/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.elastic.service;

import java.time.LocalDate;
import mx.samas.elastic.domain.VectorActivoPropiedadValor;

/**
 *
 * @author samas
 */
public interface VectorActivoPropiedadValorService {

    /**
     * @param article
     */
    void save(VectorActivoPropiedadValor article);

    /**
     * @param id
     * @return
     */
    VectorActivoPropiedadValor findOne(String id);

    /**
     * @return
     */
    Iterable<VectorActivoPropiedadValor> findAll();

    /**
     * @return
     */
    long count();

    /**
     * @param article
     */
    void delete(VectorActivoPropiedadValor article);
    
    public VectorActivoPropiedadValor findByDateAndTicker(LocalDate date, String ticker);
}
