/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Bitacora;
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

    public BitacoraOrden findOneOrdenByNombre(String nombre);

    public BitacoraOrden findOrdenById(Long id);

    public List<Bitacora> executeOrden(BitacoraOrdenEjecutorDTO orden);
}
