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
package mx.samas.controller;

import mx.samas.domain.dto.BitacoraOrdenDTO;
import mx.samas.service.BootstrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author samas
 */
@RestController
@RequestMapping("/bootstrap")
public class BootstrapController {

    @Autowired
    private BootstrapService bootstrapService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createFromDTO(BitacoraOrdenDTO dto) {
        bootstrapService.execute();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
