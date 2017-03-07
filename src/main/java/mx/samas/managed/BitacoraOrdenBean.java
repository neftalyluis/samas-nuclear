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
import mx.samas.domain.BitacoraOrden;
import mx.samas.service.BitacoraOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "bitacoraOrdenBean")
@Dependent
public class BitacoraOrdenBean {
    
    @Autowired
    private BitacoraOrdenService bitacoraOrdenService;
    
    private List<BitacoraOrden> listaOrdenes;
    
    private BitacoraOrden ordenSeleccionada;

    @PostConstruct
    public void init() {
        
        listaOrdenes = bitacoraOrdenService.getAll();
        ordenSeleccionada = new BitacoraOrden();
    }

    /**
     * @return the listaOrdenes
     */
    public List<BitacoraOrden> getListaOrdenes() {
        return listaOrdenes;
    }

    /**
     * @param listaOrdenes the listaOrdenes to set
     */
    public void setListaOrdenes(List<BitacoraOrden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    /**
     * @return the ordenSeleccionada
     */
    public BitacoraOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    /**
     * @param ordenSeleccionada the ordenSeleccionada to set
     */
    public void setOrdenSeleccionada(BitacoraOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }
    
}
