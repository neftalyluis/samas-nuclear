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

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import mx.samas.domain.Modelo;
import mx.samas.domain.Portafolio;
import mx.samas.domain.PortafolioEstatus;
import mx.samas.domain.TipoServicio;
import mx.samas.service.PortafolioEstatusService;
import mx.samas.service.TipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.samas.service.ModeloService;

/**
 *
 * @author samas
 */
@Component
@Named(value = "portafolioCrearBean")
@Dependent
public class PortafolioCrearBean {

    private Portafolio nuevo;

    private List<SelectItem> estrategiaSelectList;
    private List<SelectItem> estatusSelectList;
    private List<SelectItem> tipoServicioSelectList;

    @Autowired
    private ModeloService estrategiaService;

    @Autowired
    private PortafolioEstatusService estatusService;

    @Autowired
    private TipoServicioService tipoServicioService;

    @PostConstruct
    public void init() {
        nuevo = new Portafolio();

        estrategiaSelectList = new ArrayList<>();
        for (Modelo e : estrategiaService.getAll()) {
            estrategiaSelectList.add(new SelectItem(e, e.getNombre()));
        }

        estatusSelectList = new ArrayList<>();
        for (PortafolioEstatus pe : estatusService.getAll()) {
            estatusSelectList.add(new SelectItem(pe, pe.getNombre()));
        }

        tipoServicioSelectList = new ArrayList<>();
        for (TipoServicio ts : tipoServicioService.getAll()) {
            tipoServicioSelectList.add(new SelectItem(ts, ts.getNombre()));
        }
    }

    public void printPortafolio(ActionEvent actionEvent) {
        System.out.println(nuevo.getCuentaEje());
        System.out.println(nuevo.getEstatus().getNombre());
        System.out.println(nuevo.getEstrategia().getNombre());
        System.out.println(nuevo.getTipoServicio());

    }

    /**
     * @return the nuevo
     */
    public Portafolio getNuevo() {
        return nuevo;
    }

    /**
     * @param nuevo the nuevo to set
     */
    public void setNuevo(Portafolio nuevo) {
        this.nuevo = nuevo;
    }

    /**
     * @return the estrategiaSelectList
     */
    public List<SelectItem> getEstrategiaSelectList() {
        return estrategiaSelectList;
    }

    /**
     * @return the estatusSelectList
     */
    public List<SelectItem> getEstatusSelectList() {
        return estatusSelectList;
    }

    /**
     * @return the tipoServicioSelectList
     */
    public List<SelectItem> getTipoServicioSelectList() {
        return tipoServicioSelectList;
    }

}
