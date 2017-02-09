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
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mx.samas.domain.Portafolio;
import mx.samas.service.PortafolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author samas
 */
@Component
@Named(value = "portafolioBean")
@Dependent
public class PortafolioBean {

    private List<Portafolio> portafolioList;

    @Autowired
    private PortafolioService portafolioService;

    @PostConstruct
    public void init() {
        portafolioList = portafolioService.getAll();
    }

    /**
     * @return the portafolioList
     */
    public List<Portafolio> getPortafolioList() {
        return portafolioList;
    }

    public void showDetailPortafolio() {
        addMessage("Show it lmao");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
