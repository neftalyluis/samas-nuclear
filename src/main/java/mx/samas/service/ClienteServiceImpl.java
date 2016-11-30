/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.LinkedList;
import java.util.List;
import mx.samas.domain.Cliente;
import mx.samas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findOne(id);
    }

    @Override
    public Boolean removeClienteById(Long id) {
        clienteRepository.delete(id);
        return true;
    }

    @Override
    public Cliente createCliente(Cliente c) {
        return clienteRepository.save(c);
    }

    @Override
    public List<Cliente> getClientesFromIdList(List<Long> idList) {
        return clienteRepository.findAll(idList);
    }

}
