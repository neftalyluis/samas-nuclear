/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Perfil;

/**
 *
 * @author samas
 */
public interface PerfilService {

    public void createPerfil(Perfil p);
    
    public void createPerfilesFromList(List<Perfil> lp);
}
