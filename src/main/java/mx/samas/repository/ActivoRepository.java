/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Activo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author samas
 */
public interface ActivoRepository extends PagingAndSortingRepository<Activo, Long> {

    List<Activo> findByNombre(String nombre);

    List<Activo> findByClavePizarra(String clavePizarra);

    Activo findFirstByClavePizarra(String clavePizarra);

}
