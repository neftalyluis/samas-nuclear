/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Activo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface ActivoRepository extends PagingAndSortingRepository<Activo, Long> {

    public List<Activo> findByNombre(String nombre);

    public Activo findTopByClavePizarra(String clavePizarra);

    public Activo findFirstByClavePizarra(String clavePizarra);

    public Boolean existWithClavePizarra(String clavePizarra);

}
