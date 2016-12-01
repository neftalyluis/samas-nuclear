/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Portafolio;
import mx.samas.domain.projection.PortafolioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface PortafolioRepository extends JpaRepository<Portafolio, Long> {

    public List<Portafolio> findByCorredores(Cuenta cuenta);

    /**
     * 
     * @param id La ID del Portafolio
     * @return El portafolio proyectado en un POJO simple, si ese portafolio no 
     * tiene alguna de las relaciones regresa NULL
     */
    @Query("SELECT NEW mx.samas.domain.projection.PortafolioProjection("
            + "p.cuentaEje, "
            + "es.nombre, "
            + "ts.nombre, "
            + "p.fecha, "
            + "md.clavePizarra, "
            + "p.margen, "
            + "e.nombre) "
            + "FROM Portafolio p "
            + " JOIN p.estrategia es"
            + " JOIN p.tipoServicio ts"
            + " JOIN p.monedaDenominacion md"
            + " JOIN p.estatus e "
            + "WHERE p.id = :idPortafolio")
    public PortafolioProjection findById(@Param("idPortafolio") Long id);
}
