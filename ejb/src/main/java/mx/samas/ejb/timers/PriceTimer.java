/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Timer;

/**
 *
 * @author neftaly
 */
@Stateless
@LocalBean
public class PriceTimer {

    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Long executionId = jobOperator.start("VectorAnaliticoJob", new Properties());
        JobExecution jobExecution = jobOperator.getJobExecution(executionId);

        timer.cancel();
    }
}
