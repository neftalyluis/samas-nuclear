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

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface PortafolioRepository extends JpaRepository<Portafolio, Long> {

    public List<Portafolio> findByCorredores(Cuenta cuenta);

    @Query("")
    public PortafolioProjection findById(Long id);
}
