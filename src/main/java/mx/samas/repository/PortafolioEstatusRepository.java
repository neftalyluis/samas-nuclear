/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.PortafolioEstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface PortafolioEstatusRepository extends JpaRepository<PortafolioEstatus, Long> {

    public PortafolioEstatus findOneByNombre(String nombre);
    
    @Query("SELECT pe FROM PortafolioEstatus pe WHERE pe.orden = 0")
    public PortafolioEstatus getEstatusInicial();
}
