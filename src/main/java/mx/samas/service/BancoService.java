/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import mx.samas.domain.Banco;

/**
 *
 * @author samas
 */
public interface BancoService {

    public void createBanco(Banco b);

    public Banco getByNombre(String nombre);

}
