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
import mx.samas.domain.VectorModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.samas.service.VectorModeloService;

/**
 *
 * @author samas
 */
@Named(value = "vectorPortafolioModeloBean")
@Dependent
@Component
public class VectorPortafolioModeloBean {

   private List<VectorModelo> vectorPortafolioModeloList;
   
   @Autowired
   private VectorModeloService vectorPortafolioModeloService;
   private VectorModelo vectorPortafolioModelo = new VectorModelo();
   private PortafolioModeloDTO portafolioModeloDTO = new PortafolioModeloDTO();
   
   @PostConstruct
   public void init(){
       vectorPortafolioModeloList = vectorPortafolioModeloService.getAll();
   }
   
   public List<VectorModelo> getVectorPortafolioModeloList(){
       return vectorPortafolioModeloList;   
   }
   
    public String verVectoresPortafolioModelo(){
        return "tablaVectorPortafolioModelo.xhtml";
   }
   
   public String agregarNuevoVectorPortafolioModelo(){
       vectorPortafolioModeloService.createNewPortafolioModeloListForEstrategia(Long.MIN_VALUE, getPortafolioModeloDTO());
       vectorPortafolioModeloList = vectorPortafolioModeloService.getAll();
       return "tablaVectorPortafolioModelo.xhtml";
   }

    /**
     * @return the vectorPortafolioModelo
     */
    public VectorModelo getVectorPortafolioModelo() {
        return vectorPortafolioModelo;
    }

    /**
     * @param vectorPortafolioModelo the vectorPortafolioModelo to set
     */
    public void setVectorPortafolioModelo(VectorModelo vectorPortafolioModelo) {
        this.vectorPortafolioModelo = vectorPortafolioModelo;
    }

    /**
     * @return the portafolioModeloDTO
     */
    public PortafolioModeloDTO getPortafolioModeloDTO() {
        return portafolioModeloDTO;
    }

    /**
     * @param portafolioModeloDTO the portafolioModeloDTO to set
     */
    public void setPortafolioModeloDTO(PortafolioModeloDTO portafolioModeloDTO) {
        this.portafolioModeloDTO = portafolioModeloDTO;
    }
    
}
