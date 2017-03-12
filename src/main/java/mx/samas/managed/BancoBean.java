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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import mx.samas.domain.Banco;
import mx.samas.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dmct
 */
@Named(value = "bancoBean")
@SessionScoped
public class BancoBean implements Serializable{
    
    private List<Banco> listBancos;
    private Banco banco;
    
    @Autowired
    private BancoService bancoService;
    
    @PostConstruct
    public void init(){
        this.listBancos = bancoService.getAllBanco();    
        banco = new Banco();
    }
    
    public String createBanco(){
        bancoService.createBanco(banco);
        listBancos = bancoService.getAllBanco();
        return "tablaBanco.xhtml";
    }

    /**
     * @return the listBancos
     */
    public List<Banco> getListBancos() {
        return listBancos;
    }

    /**
     * @param listBancos the listBancos to set
     */
    public void setListBancos(List<Banco> listBancos) {
        this.listBancos = listBancos;
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    
}
