/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.Date;
import java.util.List;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPosicion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface VectorPosicionRepository extends PagingAndSortingRepository<VectorPosicion, Long> {

    public List<VectorPosicion> findByPortafolioAndFecha(Portafolio portafolio, Date fecha);
}
