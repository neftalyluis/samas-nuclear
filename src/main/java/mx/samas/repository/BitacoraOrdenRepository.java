/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.BitacoraOrden;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author samas
 */
public interface BitacoraOrdenRepository extends JpaRepository<BitacoraOrden, Long> {

    public BitacoraOrden findOneByNombre(String nombre);
}
