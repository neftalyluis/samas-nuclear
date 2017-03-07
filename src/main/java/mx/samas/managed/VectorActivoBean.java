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
import mx.samas.domain.VectorActivo;
import mx.samas.service.VectorActivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "vectorActivoBean")
@Dependent
public class VectorActivoBean {

    /**
     * Creates a new instance of VectorActivoBean
     */
    private List<VectorActivo> vectorActivoList;

    @Autowired
    private VectorActivoService vectorActivoService;
    private VectorActivo vectorActivo = new VectorActivo();

    @PostConstruct
    public void init() {
        vectorActivoList = vectorActivoService.getAllVectores();
    }

    /**
     * @return the vectorActivoList
     */
    public List<VectorActivo> getVectorActivoList() {
        return vectorActivoList;
    }
    
    public String verVectoresActivo(){
        return "tablaVectorActivo.xhtml";
   }
   
   public String agregarNuevoVectorActivo(){
        setVectorActivo(vectorActivoService.createVectorActivo(getVectorActivo()));
       vectorActivoList = vectorActivoService.getAllVectores();
       return "tablaVectorActivo.xhtml";
   }

    /**
     * @return the vectorActivo
     */
    public VectorActivo getVectorActivo() {
        return vectorActivo;
    }

    /**
     * @param vectorActivo the vectorActivo to set
     */
    public void setVectorActivo(VectorActivo vectorActivo) {
        this.vectorActivo = vectorActivo;
    }
    
}
