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
import mx.samas.rule.ReglasOperablesBool;

/**
 *
 * @author samas
 */
@Entity
public class ReglaBooleano extends Regla {

    private ReglasOperablesBool reglaBooleana;

    /**
     * @return the reglaBooleana
     */
    public ReglasOperablesBool getReglaBooleana() {
        return reglaBooleana;
    }

    /**
     * @param reglaBooleana the reglaBooleana to set
     */
    public void setReglaBooleana(ReglasOperablesBool reglaBooleana) {
        this.reglaBooleana = reglaBooleana;
    }

}
