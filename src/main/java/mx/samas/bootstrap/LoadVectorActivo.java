/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.bootstrap;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

/**
 *
 * @author samas
 */
public class LoadVectorActivo implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = Logger.getLogger(LoadVectorActivo.class.getName());
    
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent e) {
        LOG.info("Batch VectorActivo");
        Job job = (Job) appContext.getBean("agregarVectorActivoPropiedadValorsJob");

        
        JobParameters jpb = new JobParameters();

        try {
            JobExecution a = jobLauncher.run(job, jpb);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
            Logger.getLogger(LoadVectorActivo.class.getName()).log(Level.SEVERE, "El Batch de Vector falló", ex);
        }
    }

}
