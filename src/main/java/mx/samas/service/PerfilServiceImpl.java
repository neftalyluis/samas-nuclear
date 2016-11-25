/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Perfil;
import mx.samas.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void createPerfil(Perfil p) {
        perfilRepository.save(p);
    }

    @Override
    public void createPerfilesFromList(List<Perfil> lp) {
        perfilRepository.save(lp);
    }

}
