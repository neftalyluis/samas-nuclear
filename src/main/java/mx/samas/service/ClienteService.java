/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Cliente;

/**
 *
 * @author samas
 */
public interface ClienteService {

    public List<Cliente> getAllClientes();

    public Cliente getClienteById(Long id);

    public Boolean removeClienteById(Long id);

    public Cliente createCliente(Cliente c);

}
