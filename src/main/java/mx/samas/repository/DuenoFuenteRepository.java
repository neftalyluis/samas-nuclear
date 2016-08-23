/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.DuenoFuente;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface DuenoFuenteRepository extends PagingAndSortingRepository<DuenoFuente, Long> {

    public DuenoFuente findByNombre(String nombre);
}
