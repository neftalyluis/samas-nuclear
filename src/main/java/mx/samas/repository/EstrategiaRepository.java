/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.projection.EstrategiaModeloProjection;
import mx.samas.domain.projection.EstrategiaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface EstrategiaRepository extends JpaRepository<Estrategia, Long> {

    /**
     *
     * @param id
     * @return La ID de la Estrategia
     */
    @Query("SELECT NEW mx.samas.domain.projection.EstrategiaProjection("
            + "e.id, e.nombre, e.liquidez, e.efectivo, e.grupos, e.margen) "
            + "FROM Estrategia e WHERE e.id= :idEstrategia")
    public EstrategiaProjection findOneProjectedById(@Param("idEstrategia") Long id);

    /**
     *
     * @param id
     * @return
     */
    @Query("SELECT NEW mx.samas.domain.projection.EstrategiaModeloProjection("
            + "vpm.creado, "
            + "f.nombre, "
            + "vpm.diana) "
            + "FROM VectorPortafolioModelo vpm "
            + "JOIN vpm.estrategia es "
            + "JOIN vpm.fungibilidad f "
            + "WHERE es.id= :idEstrategia "
            + "AND vpm.creado= (SELECT MAX(vp.creado) "
            + "FROM VectorPortafolioModelo vp WHERE vp.estrategia.id= :idEstrategia) ")
    public List<EstrategiaModeloProjection> getLastModeloWithIdEstrategia(@Param("idEstrategia") Long id);
}
