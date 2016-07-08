/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repositories;

import java.util.List;
import mx.samas.domain.Activo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author samas
 */
public interface ActivoRepository extends CrudRepository<Activo, Long> {

    List<Activo> findByNombre(String nombre);

}
