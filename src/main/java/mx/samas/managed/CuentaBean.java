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

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import mx.samas.domain.Banco;
import mx.samas.domain.Cuenta;
import mx.samas.service.BancoService;
import mx.samas.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
@Named(value = "cuentaBean")
@SessionScoped
public class CuentaBean implements Serializable{

    /**
     * Creates a new instance of CuentaBean
     */
   private List<Cuenta> cuentaList;
   
   private List<Banco> bancos;
   
   @Autowired
   private CuentaService cuentaService;
   @Autowired
   private BancoService bancoService;
   
   private Cuenta cuenta = new Cuenta();
   
   
   @PostConstruct
   public void init(){
       cuentaList = cuentaService.getAll();
       bancos = bancoService.getAllBanco();
   }
   
   public Banco getBanco(Long id){
       return bancoService.getBancoById(id);
   }
   
   public List<Cuenta> getCuentaList(){
       return cuentaList;   
   }
   
   public String verCuentas(){
        return "tablaCuenta.xhtml";
   }
   
   public String agregarNuevaCuenta(){
        setCuenta(cuentaService.createOrUpdateCuenta(getCuenta()));
       cuentaList = cuentaService.getAll();
       return "tablaCuenta.xhtml";
   }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the bancos
     */
    public List<Banco> getBancos() {
        return bancos;
    }

    /**
     * @param bancos the bancos to set
     */
    public void setBancos(List<Banco> bancos) {
        this.bancos = bancos;
    }
    
}
