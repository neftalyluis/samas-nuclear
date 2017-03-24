/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.time.LocalDate;
import java.util.List;
import mx.samas.domain.Modelo;
import mx.samas.domain.VectorModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface PortafolioModeloRepository extends JpaRepository<VectorModelo, Long> {

    public List<VectorModelo> findByEstrategiaAndCreado(Modelo estrategia, LocalDate creado);
}
