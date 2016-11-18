/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.elastic.job;

import java.util.List;
import mx.samas.domain.elastic.VectorActivoPropiedadValor;
import mx.samas.repository.elastic.VectorActivoPropiedadValorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
class VectorActivoPropiedadValorItemWriter implements ItemWriter<VectorActivoPropiedadValor> {


    @Autowired
    private VectorActivoPropiedadValorRepository vectorService;

    @Override
    public void write(List<? extends VectorActivoPropiedadValor> items) throws Exception {
        vectorService.save(items);
    }
    
}
