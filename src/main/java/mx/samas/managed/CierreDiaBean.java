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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import mx.samas.service.BatchService;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
@Named(value = "cierreDiaBean")
@Dependent
public class CierreDiaBean {

    private static final Logger LOG = Logger.getLogger(CierreDiaBean.class.getName());

    private Date fechaCalendario;

    private UploadedFile archivo;

    @Autowired
    private BatchService batchService;

    @Value("${samas.pip.directory}")
    private String directoryVector;

    private String rutaFinal;

    private final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    @PostConstruct
    public void init() {

    }

    public void upload() {
        LOG.info(getArchivo().getFileName());
        LOG.info(getFechaCalendario().toString());
        InputStream archivoStream = null;
        OutputStream archivoEnDisco = null;
        rutaFinal = directoryVector + "/Vector" + formatter.format(fechaCalendario) + ".csv";
        if (getArchivo() != null) {
            try {
                archivoStream = getArchivo().getInputstream();
                archivoEnDisco = new FileOutputStream(new File(rutaFinal));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = archivoStream.read(bytes)) != -1) {
                    archivoEnDisco.write(bytes, 0, read);
                }
            } catch (IOException ex) {
                Logger.getLogger(CierreDiaBean.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (archivoStream != null) {
                    try {
                        archivoStream.close();
                    } catch (IOException e) {
                        LOG.warning(e.toString());
                    }
                }
                if (archivoEnDisco != null) {
                    try {
                        archivoEnDisco.close();
                    } catch (IOException e) {
                        LOG.warning(e.toString());
                    }
                }
                launchVectorJob();
            }
        }
    }

    public void launchVectorJob() {
        batchService.cierreDia(rutaFinal, getFechaCalendario());
    }

    /**
     * @return the fechaCalendario
     */
    public Date getFechaCalendario() {
        return fechaCalendario;
    }

    /**
     * @param fechaCalendario the fechaCalendario to set
     */
    public void setFechaCalendario(Date fechaCalendario) {
        this.fechaCalendario = fechaCalendario;
    }

    /**
     * @return the archivo
     */
    public UploadedFile getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
}
