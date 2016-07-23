/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.Bitacora;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface BitacoraRepository extends PagingAndSortingRepository<Bitacora, Long> {

    public List<Bitacora> findByActivo(Activo activo);

}
