/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.jobs;

import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author samas
 */
public class TestJob implements Job{

    private static final Logger LOG = Logger.getLogger(TestJob.class.getName());

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        LOG.info("Dis is test for Cron Jobs");
    }
    
}
