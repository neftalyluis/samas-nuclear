/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPosicion;

/**
 *
 * @author samas
 */
public interface VectorPosicionService {

    public List<VectorPosicion> getLastPosicionesFromPortafolio(Portafolio p);

    public void persistPosiciones(List<VectorPosicion> listVector);
}
