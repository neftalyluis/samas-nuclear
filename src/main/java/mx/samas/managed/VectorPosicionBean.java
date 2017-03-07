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
import mx.samas.domain.VectorPosicion;
import mx.samas.service.VectorPosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Named(value = "vectorPosicionBean")
@Dependent
@Component
public class VectorPosicionBean {

   private List<VectorPosicion> vectorPosicionList;
   
   @Autowired
   private VectorPosicionService vectorPosicionService;
   private VectorPosicion vectorPosicion = new VectorPosicion();
   
   @PostConstruct
   public void init(){
       vectorPosicionList = vectorPosicionService.getAllPosiciones();
   }
   
   public List<VectorPosicion> getVectorPosicionList(){
       return vectorPosicionList;   
   }
    
   public String verVectoresPosicion(){
        return "tablaVectorPosicion.xhtml";
   }
   
   public String agregarNuevoVectorPosicion(){
       vectorPosicionService.persistPosiciones(vectorPosicionList);
       vectorPosicionList = vectorPosicionService.getAllPosiciones();
       return "tablaVectorPosicion.xhtml";
   }

    /**
     * @return the vectorPosicion
     */
    public VectorPosicion getVectorPosicion() {
        return vectorPosicion;
    }

    /**
     * @param vectorPosicion the vectorPosicion to set
     */
    public void setVectorPosicion(VectorPosicion vectorPosicion) {
        this.vectorPosicion = vectorPosicion;
    }
    
}
