/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.ActivoPropiedad;

/**
 *
 * @author samas
 */
public interface ActivoPropiedadService {

    public List<ActivoPropiedad> getPropiedades();

    public ActivoPropiedad getPropiedadWithNombre(String nombre);

    public ActivoPropiedad getPropiedadWithId(Long id);
    
    public ActivoPropiedad createPropiedad(ActivoPropiedad a);
}