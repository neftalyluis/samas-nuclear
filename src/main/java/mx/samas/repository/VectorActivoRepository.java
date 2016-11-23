/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.Activo;
import mx.samas.domain.VectorActivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface VectorActivoRepository extends JpaRepository<VectorActivo, Long> {

    public VectorActivo findFirstByActivoOrderByFechaDesc(Activo a);
}
