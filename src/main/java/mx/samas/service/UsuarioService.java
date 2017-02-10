/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Usuario;

/**
 *
 * @author samas
 */
public interface UsuarioService {

    public Usuario getByUsername(String username);

    public Usuario createUsuario(Usuario u);
    
    public List<Usuario> getAllUsuarios();
}
