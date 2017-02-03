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
import mx.samas.domain.Activo;
import mx.samas.service.ActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "activoBean")
@Dependent
public class ActivoBean {

    private List<Activo> activoList;

    @Autowired
    private ActivoService activoService;

    @PostConstruct
    public void init() {
        activoList = activoService.getAllActivos();
    }

    /**
     * @return the activoList
     */
    public List<Activo> getActivoList() {
        return activoList;
    }

}
