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
package mx.samas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import mx.samas.domain.BitacoraOrden;
import mx.samas.service.BitacoraOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
@FacesConverter("bitacoraOrdenConverter")
public class BitacoraOrdenConverter implements Converter {

    @Autowired
    private BitacoraOrdenService bitacoraOrdenService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return bitacoraOrdenService.findOrdenById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        BitacoraOrden bo = (BitacoraOrden) value;
        return String.valueOf(bo.getId());
    }

}
