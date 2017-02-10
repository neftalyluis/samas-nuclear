/*
 * Copyright 2017 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.samas.managed;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import mx.samas.domain.Usuario;
import mx.samas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "usuarioBean")
@Dependent
public class UsuarioBean {
    
    private List<Usuario> usuarioList;
    
    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    public void init(){
        usuarioList = usuarioService.getAllUsuarios();
    }
    
    /**
     * @return the usuarioList
     */
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }
    
    
}
