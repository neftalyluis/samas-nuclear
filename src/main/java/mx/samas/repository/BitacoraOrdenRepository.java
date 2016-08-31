/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.BitacoraOrden;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author samas
 */
public interface BitacoraOrdenRepository extends PagingAndSortingRepository<BitacoraOrden, Long> {

    public BitacoraOrden findByNombre(String nombre);
}
