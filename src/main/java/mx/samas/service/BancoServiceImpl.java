/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.Banco;
import mx.samas.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
public class BancoServiceImpl implements BancoService{
    
    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public void createBanco(Banco b) {
        bancoRepository.save(b);
    }
    
}
