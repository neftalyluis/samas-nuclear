/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import mx.samas.domain.Perfil;
import mx.samas.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

/**
 * DTO para unir la entidad Usuario de SAMAS y el UserDetails de Spring Security
 *
 * @author samas
 */
public class SecurityUser implements UserDetails {

    private List<String> usuarioPerfiles;
    private String username;
    private String password;

    /**
     *
     * Constructor para crear el DTO apartir de la entidad
     *
     * @param u El usuario a convertirse en DTO
     */
    public SecurityUser(Usuario u) {
        if (u != null) {
            password = u.getPassword();
            username = u.getNombreUsuario();
            
            usuarioPerfiles = new ArrayList<>();
            for (Perfil p : u.getPerfiles()) {
                usuarioPerfiles.add(p.getNombre());
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String perfiles = StringUtils.collectionToCommaDelimitedString(getUsuarioPerfiles());
        return AuthorityUtils.commaSeparatedStringToAuthorityList(perfiles);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getUsuarioPerfiles() {
        return usuarioPerfiles;
    }

    public void setUsuarioPerfiles(List<String> usuarioPerfiles) {
        this.usuarioPerfiles = usuarioPerfiles;
    }

}
