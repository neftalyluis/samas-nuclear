/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.configuration;

import mx.samas.domain.Usuario;
import mx.samas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**Esta clase crea un usuario de Spring a partir de la base de datos,
 *si el nombre ingresado es nulo (null) arrojara un mensaje afirmando que no se encontro el usuario.  
 * 
 * @author samas
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String string) {
        Usuario user = usuarioService.getByUsername(string);
        if (user == null) {
            throw new UsernameNotFoundException("No se encontro el usuario con Nombre: " + string);
        }
        return new SecurityUser(user);
    }

}
