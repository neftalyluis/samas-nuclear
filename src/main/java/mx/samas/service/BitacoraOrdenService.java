/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.dto.BitacoraOrdenDTO;
import mx.samas.domain.dto.BitacoraOrdenEjecutorDTO;

/**
 *
 * @author samas
 */
public interface BitacoraOrdenService {

    public BitacoraOrden createOrden(BitacoraOrdenDTO dto);

    public BitacoraOrden createOrden(BitacoraOrden dto);

    public BitacoraOrden findOrdenByNombre(String nombre);

    public BitacoraOrden findOrdenById(Long id);

    public void executeOrden(BitacoraOrdenEjecutorDTO orden);
}
