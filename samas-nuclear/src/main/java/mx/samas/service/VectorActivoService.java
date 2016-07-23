/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.VectorActivo;

/**
 *
 * @author samas
 */
public interface VectorActivoService {

    public List<VectorActivo> getVectorFromActivo(Long id);

    public List<VectorActivo> getVectorFromActivo(String clavePizarra);

    public VectorActivo addVectorToActivo(Long id, VectorActivo vector);
}
