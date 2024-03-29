/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import mx.samas.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

    /**
     * @param nombre Variable de tipo cadena que almacenara el nombre de un Banco.
     * @return El nombre del Banco.
     */
    public Banco findByNombreContaining(String nombre);
}
