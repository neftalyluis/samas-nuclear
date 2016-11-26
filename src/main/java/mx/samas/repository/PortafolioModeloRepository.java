/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.Date;
import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.VectorPortafolioModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface PortafolioModeloRepository extends JpaRepository<VectorPortafolioModelo, Long> {

    public List<VectorPortafolioModelo> findByEstrategiaAndCreado(Estrategia estrategia, Date creado);
}
