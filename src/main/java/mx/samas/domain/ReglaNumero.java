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
package mx.samas.domain;

import javax.persistence.Entity;
import mx.samas.rule.ReglasOperablesNumero;

/**
 *
 * @author samas
 */
@Entity
public class ReglaNumero extends Regla {

    private ReglasOperablesNumero reglaNumero;

    /**
     * @return the reglaNumero
     */
    public ReglasOperablesNumero getReglaNumero() {
        return reglaNumero;
    }

    /**
     * @param reglaNumero the reglaNumero to set
     */
    public void setReglaNumero(ReglasOperablesNumero reglaNumero) {
        this.reglaNumero = reglaNumero;
    }

}