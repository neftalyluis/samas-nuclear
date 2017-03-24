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

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mx.samas.domain.Activo;
import mx.samas.elastic.domain.VectorActivoPropiedadValor;
import mx.samas.elastic.service.VectorActivoPropiedadValorService;
import mx.samas.service.ActivoService;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "propiedadesBean")
@Dependent
public class PropiedadesBean {

    private static final Logger LOG = Logger.getLogger(PropiedadesBean.class.getName());

    private Activo activoSeleccionado;
    private List<Activo> activoLista;
    private VectorActivoPropiedadValor vector;

    @Autowired
    private ActivoService activoService;
    @Autowired
    private VectorActivoPropiedadValorService vectorPropiedadService;

    @PostConstruct
    public void init() {
        activoLista = activoService.getAllActivos();
        vector = new VectorActivoPropiedadValor();

    }

    public void cargarVector(SelectEvent event) {
        Date fechaSeleccionada = (Date) event.getObject();
        try {
            vector = vectorPropiedadService.findByDateAndTicker(
                    fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    activoSeleccionado.getClavePizarra());
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "No existe vector para ese dia y activo"));
        }
    }

    /**
     * @return the activoSeleccionado
     */
    public Activo getActivoSeleccionado() {
        return activoSeleccionado;
    }

    /**
     * @param activoSeleccionado the activoSeleccionado to set
     */
    public void setActivoSeleccionado(Activo activoSeleccionado) {
        this.activoSeleccionado = activoSeleccionado;
    }

    /**
     * @return the activoLista
     */
    public List<Activo> getActivoLista() {
        return activoLista;
    }

    /**
     * @param activoLista the activoLista to set
     */
    public void setActivoLista(List<Activo> activoLista) {
        this.activoLista = activoLista;
    }

    /**
     * @return the vector
     */
    public VectorActivoPropiedadValor getVector() {
        return vector;
    }

    /**
     * @param vector the vector to set
     */
    public void setVector(VectorActivoPropiedadValor vector) {
        this.vector = vector;
    }

}
