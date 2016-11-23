/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.Date;
import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.Bitacora;
import mx.samas.domain.Portafolio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para Bitacora
 *
 * @author samas
 */
@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {

    /**
     * Busca todas las entradas de la Bitacora (sin importar rango de fecha) que
     * tengan relacion con un Activo
     *
     * @param activo El Activo que esta relacionado
     * @return La lista de entradas de la bitacora que hayan operado ese activo
     */
    public List<Bitacora> findByActivo(Activo activo);

    /**
     * Busca todas las entradas de la Bitacora que han operado hasta la fecha
     * dicha y que tengan una liquidacion en otra fecha
     *
     * @param p El portafolio que esta relacionado
     * @param fechaActual La "fecha de Hoy", aunque puede ser cualquier fecha
     * @param fechaValor La fecha maxima en la cual va relacionada con la
     * liquidacion
     * @return La lista que cumpla esos requerimientos
     */
    @Query(value = "SELECT b FROM Bitacora b WHERE b.contratoServicio = :portafolio "
            + "AND b.fechaEjecucion <= :fechaActual "
            + "AND b.fechaLiquidacion <= :fechaValor")
    public List<Bitacora> findByPortafolioAndOperacionAndLiquidacion(
            @Param("portafolio") Portafolio p,
            @Param("fechaActual") Date fechaActual,
            @Param("fechaValor") Date fechaValor);
}
