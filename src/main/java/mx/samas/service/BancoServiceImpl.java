/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Banco;
import mx.samas.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class BancoServiceImpl implements BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public Banco createBanco(Banco b) {
        return bancoRepository.save(b);
    }

    @Override
    public Banco getByNombre(String nombre) {
        return bancoRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<Banco> getAllBanco() {
        return bancoRepository.findAll();
    }

    @Override
    public Boolean removeBancoById(Long id) {
        bancoRepository.delete(id);
        return true;
    }

    @Override
    public Banco updateBancoById(Banco b) {
        return bancoRepository.save(b);
    }

}
