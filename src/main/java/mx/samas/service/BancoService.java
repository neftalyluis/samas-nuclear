/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Banco;

/**
 *
 * @author samas
 */
public interface BancoService {

    public Banco createBanco(Banco b);

    public Banco getByNombre(String nombre);
    
    public List<Banco> getAllBanco();
    
    public Boolean removeBancoById(Long id);
    
    public Banco updateBancoById(Banco b);

}
