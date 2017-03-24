/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.devengos;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import mx.samas.domain.CuentaCorredor;
import mx.samas.domain.VectorPosicionCredito;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author samas
 */
public class DevengoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory emFactory;

    @Bean
    public Job devengoJob() {
        return jobs.get("devengoJob")
                .start(devengoStep())
                .build();
    }

    @Bean
    public Step devengoStep() {
        return stepBuilderFactory.get("devengosPosicionStep")
                .<CuentaCorredor, List<VectorPosicionCredito>>chunk(1000)
                .reader(posicionReader())
                .faultTolerant()
                .build();

    }

    @Bean(destroyMethod = "")
    @StepScope
    public JpaPagingItemReader<CuentaCorredor> posicionReader() {
        JpaPagingItemReader<CuentaCorredor> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emFactory);
        reader.setQueryString("SELECT c FROM Cuenta c "
                + "JOIN FETCH a.portafolios p");
        return reader;
    }
}
