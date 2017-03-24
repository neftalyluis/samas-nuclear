/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.repository;

import java.util.List;
import mx.samas.domain.CuentaCorredor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author samas
 */
@Repository
public interface CuentaRepository extends JpaRepository<CuentaCorredor, Long> {

    /**
     * @param idCuenta Variable de tipo que cadena que almacenara el id de una CuentaCorredor.
     * @return La cuenta asignada a un id.
     */
    public CuentaCorredor getByIdCuenta(String idCuenta);
    
    @Query("SELECT c.idCuenta FROM Cuenta c")
    public List<String> getAllIdCuenta();
}
