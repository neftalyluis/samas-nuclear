/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository.elastic;

import mx.samas.domain.elastic.VectorActivoPropiedadValor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface VectorActivoPropiedadValorRepository extends ElasticsearchRepository<VectorActivoPropiedadValor, String>{

}
