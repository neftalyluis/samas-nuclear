/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    public Transaccion getByNombre(String nombre);
}
