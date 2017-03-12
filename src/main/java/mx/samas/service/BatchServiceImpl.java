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
package mx.samas.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import mx.samas.job.SAMASJobs;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class BatchServiceImpl implements BatchService {

    private static final Logger LOG = Logger.getLogger(BatchServiceImpl.class.getName());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext appContext;

    @Override
    public Boolean cierreDia(String fileToRead, Date fechaCierre) {

        Map<String, JobParameter> params = new HashMap<>();
        params.put("archivo", new JobParameter(fileToRead));
        params.put("fecha", new JobParameter(fechaCierre));
        Job job = (Job) appContext.getBean(SAMASJobs.VALUACION_VECTOR.toString());
        JobParameters jpb = new JobParameters(params);

        try {
            JobExecution a = jobLauncher.run(job, jpb);
            return true;
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
            LOG.warning(ex.toString());
            return false;
        }
    }

    @Override
    public Boolean bootstrapActivo() {
        Map<String, JobParameter> params = new HashMap<>();
        Job job = (Job) appContext.getBean(SAMASJobs.BOOTSTRAP_ACTIVO.toString());
        JobParameters jpb = new JobParameters(params);

        try {
            JobExecution a = jobLauncher.run(job, jpb);
            return true;
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
            LOG.warning(ex.toString());
            return false;
        }
    }

}
