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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author samas
 */
public class SecurityUser extends Usuario implements UserDetails {

    //Perdoname madre por mi vida loca D;<
    public SecurityUser(Usuario u) {
        if (u != null) {
            this.setId(u.getId());
            this.setNombreCompleto(u.getNombreCompleto());
            this.setNombreUsuario(u.getNombreUsuario());
            this.setPassword(u.getPassword());
            this.setPerfiles(u.getPerfiles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Perfil> perfiles = super.getPerfiles();
        if (perfiles != null) {
            for (Perfil perfil : perfiles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perfil.getNombre());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getNombreUsuario();
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

}
