/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Transaccion;
import mx.samas.domain.projection.TransaccionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    public Transaccion getByNombre(String nombre);

    @Query("SELECT NEW mx.samas.domain.projection.TransaccionProjection("
            + "t.id, "
            + "ft.nombre, "
            + "t.nombre, "
            + "t.credito, "
            + "t.flujoTitulos, "
            + "t.flujoEfectivo) "
            + "FROM Transaccion t "
            + "JOIN t.fuenteTransaccion ft "
            + "WHERE t.nombre= :nombre")
    public TransaccionProjection getProjectedByNombre(@Param("nombre") String nombre);

    @Query("SELECT NEW mx.samas.domain.projection.TransaccionProjection("
            + "t.id, "
            + "ft.nombre, "
            + "t.nombre, "
            + "t.credito, "
            + "t.flujoTitulos, "
            + "t.flujoEfectivo) "
            + "FROM Transaccion t "
            + "JOIN t.fuenteTransaccion ft"
            + "WHERE t.id= :idTransaccion")
    public TransaccionProjection getProjectedById(@Param("idTransaccion") Long id);

    @Query("SELECT NEW mx.samas.domain.projection.TransaccionProjection("
            + "t.id, "
            + "ft.nombre, "
            + "t.nombre, "
            + "t.credito, "
            + "t.flujoTitulos, "
            + "t.flujoEfectivo) "
            + "FROM Transaccion t "
            + "JOIN t.fuenteTransaccion ft")
    public List<TransaccionProjection> getAllProjected();

    @Query("SELECT NEW mx.samas.domain.projection.TransaccionProjection("
            + "t.id, "
            + "ft.nombre, "
            + "t.nombre, "
            + "t.credito, "
            + "t.flujoTitulos, "
            + "t.flujoEfectivo) "
            + "FROM Transaccion t "
            + "JOIN t.fuenteTransaccion ft "
            + "WHERE ft.nombre= :nombre")
    public List<TransaccionProjection> getProjectedByDuenoFuenteNombre(@Param("nombre") String nombre);

}
