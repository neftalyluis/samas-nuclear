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
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.Regla;
import mx.samas.repository.ReglaRepository;
import mx.samas.service.ActivoPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
@Named(value = "reglaBean")
@Dependent
public class ReglaBean {

    @Autowired
    private ActivoPropiedadService propiedadService;

    @Autowired
    private ReglaRepository reglaRepository;

    private List<ActivoPropiedad> propiedadesList;
    private Regla nuevaRegla;

    @PostConstruct
    public void init() {
        this.propiedadesList = propiedadService.getPropiedades();

    }

    /**
     * @return the propiedadesList
     */
    public List<ActivoPropiedad> getPropiedadesList() {
        return propiedadesList;
    }

    /**
     * @return the nuevaRegla
     */
    public Regla getNuevaRegla() {
        return nuevaRegla;
    }

    /**
     * @param nuevaRegla the nuevaRegla to set
     */
    public void setNuevaRegla(Regla nuevaRegla) {
        this.nuevaRegla = nuevaRegla;
    }

}
