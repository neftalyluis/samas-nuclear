/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.dto.EstrategiaDTO;

/**
 *
 * @author samas
 */
public interface EstrategiaService {

    public List<Estrategia> getAll();

    public Estrategia createFromDTO(EstrategiaDTO estrategia);

}
