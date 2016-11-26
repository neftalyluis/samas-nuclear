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
 *
 * @author samas
 */
public class SecurityUser extends Usuario implements UserDetails {

    private List<String> usuarioPerfiles;

    //Perdoname madre por mi vida loca D;<
    public SecurityUser(Usuario u) {
        if (u != null) {
            usuarioPerfiles = new ArrayList<>();
            this.setId(u.getId());
            this.setNombreCompleto(u.getNombreCompleto());
            this.setNombreUsuario(u.getNombreUsuario());
            this.setPassword(u.getPassword());
            this.setPerfiles(u.getPerfiles());
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

    /**
     * @return the usuarioPerfiles
     */
    public List<String> getUsuarioPerfiles() {
        return usuarioPerfiles;
    }

    /**
     * @param usuarioPerfiles the usuarioPerfiles to set
     */
    public void setUsuarioPerfiles(List<String> usuarioPerfiles) {
        this.usuarioPerfiles = usuarioPerfiles;
    }

}
