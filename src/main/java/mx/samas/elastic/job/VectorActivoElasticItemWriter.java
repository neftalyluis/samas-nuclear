/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.elastic.job;

import java.util.List;
import mx.samas.elastic.domain.VectorActivoPropiedadValor;
import mx.samas.elastic.repository.VectorActivoPropiedadValorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
class VectorActivoElasticItemWriter implements ItemWriter<VectorActivoPropiedadValor> {


    @Autowired
    private VectorActivoPropiedadValorRepository vectorService;

    @Override
    public void write(List<? extends VectorActivoPropiedadValor> items) throws Exception {
        vectorService.save(items);
    }
    
}
