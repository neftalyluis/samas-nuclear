/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface ActivoPropiedadRepository extends PagingAndSortingRepository<ActivoPropiedad, Long> {

    public ActivoPropiedad findByNombre(String nombre);

////    @Query("SELECT a.propiedades FROM Activo a WHERE a.clavePizarra= :clavePizarra")
////    public List<ActivoPropiedad> getAllPropiedadesWithClavePizarra(@Param("clavePizarra") String clavePizarra);
//    @Query("SELECT ap FROM ActivoPropiedad ap WHERE ")
//    public List<ActivoPropiedad> getAllVectorPropiedadWithActivo(@Param("activo")Activo a);
}
