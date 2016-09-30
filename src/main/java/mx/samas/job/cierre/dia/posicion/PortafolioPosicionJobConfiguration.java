/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.posicion;

import javax.persistence.EntityManagerFactory;
import mx.samas.domain.Bitacora;
import mx.samas.domain.VectorPosicion;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cuenta->VectorPosicion
 * Para cada cuenta, buscar las entradas de Bitacora que sean de Esa Cuenta y ese dia
 * Juntar con los POrtafolios y sus respectivas posiciones del dia anterior para crear los del dia
 * 
 * 
 * @author samas
 */
@Configuration
public class PortafolioPosicionJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory emFactory;

    @Bean
    public Job sumatoriaPosicionJob() {
        return jobs.get("sumatoriaPosicionJob")
                .start(sumatoriaPosicionStep())
                .build();
    }
    
    @Bean
    public Step sumatoriaPosicionStep(){
        return stepBuilderFactory.get("sumatoriaPosicionStep")
                .<Bitacora, VectorPosicion>chunk(1000)
                .reader(bitacoraReader())
                .faultTolerant()
                .build();
    }
    
    @Bean(destroyMethod = "")
    @StepScope
    public JpaPagingItemReader<Bitacora> bitacoraReader(){
        JpaPagingItemReader<Bitacora> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emFactory);
        reader.setQueryString("SELECT");
        return reader;
    }
}
