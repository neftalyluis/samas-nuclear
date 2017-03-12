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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.Transaccion;
import mx.samas.domain.dto.BitacoraOrdenEjecutorDTO;
import mx.samas.domain.dto.BitacoraOrdenValorDTO;
import mx.samas.service.BitacoraOrdenService;
import mx.samas.service.CuentaService;
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
    
    private static final Logger LOG = Logger.getLogger(BitacoraOrdenBean.class.getName());
    
    @Autowired
    private BitacoraOrdenService bitacoraOrdenService;
    
    @Autowired
    private CuentaService cuentaService;
    
    private List<BitacoraOrden> listaOrdenes;
    
    private BitacoraOrden ordenSeleccionada;
    
    private BitacoraOrdenEjecutorDTO bitacoraOrdenDTO;
    
    private List<String> cuentaList;
    
    @Autowired
    private BitacoraBean bitacoraBean;
    
    @PostConstruct
    public void init() {
        listaOrdenes = bitacoraOrdenService.getAll();
        ordenSeleccionada = new BitacoraOrden();
        bitacoraOrdenDTO = new BitacoraOrdenEjecutorDTO();
        cuentaList = cuentaService.getAllIdCuenta();
    }
    
    public void buildDTO() {
        
        bitacoraOrdenDTO.setIdOperacion(ordenSeleccionada.getId());
        
        List<BitacoraOrdenValorDTO> bovl = new ArrayList<>();
        for (Transaccion t : ordenSeleccionada.getTransacciones()) {
            BitacoraOrdenValorDTO bov = new BitacoraOrdenValorDTO(t.getId(), t.getNombre());
            bovl.add(bov);
        }
        bitacoraOrdenDTO.setValorTransacciones(bovl);
    }
    
    public String createOrden(){
        bitacoraOrdenService.executeOrden(bitacoraOrdenDTO);
        bitacoraBean.init();
        return "bitacora.xhtml";
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

    /**
     * @return the bitacoraOrdenDTO
     */
    public BitacoraOrdenEjecutorDTO getBitacoraOrdenDTO() {
        return bitacoraOrdenDTO;
    }

    /**
     * @param bitacoraOrdenDTO the bitacoraOrdenDTO to set
     */
    public void setBitacoraOrdenDTO(BitacoraOrdenEjecutorDTO bitacoraOrdenDTO) {
        this.bitacoraOrdenDTO = bitacoraOrdenDTO;
    }

    /**
     * @return the cuentaList
     */
    public List<String> getCuentaList() {
        return cuentaList;
    }

    /**
     * @param cuentaList the cuentaList to set
     */
    public void setCuentaList(List<String> cuentaList) {
        this.cuentaList = cuentaList;
    }
    
}
