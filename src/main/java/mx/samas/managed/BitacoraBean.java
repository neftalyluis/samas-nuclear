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
import javax.inject.Named;
import mx.samas.domain.Bitacora;
import mx.samas.service.BitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "bitacoraBean")
@Dependent
public class BitacoraBean {

    private List<Bitacora> bitacoraList;

    @Autowired
    private BitacoraService bitacoraService;

    /**
     * No solicita ningun parametro y no regresa nada.
     */
    @PostConstruct
    public void init() {
        bitacoraList = bitacoraService.getAllEntries();

    }

    /**
     * @return the bitacoraList
     */
    public List<Bitacora> getBitacoraList() {
        return bitacoraList;
    }

}
