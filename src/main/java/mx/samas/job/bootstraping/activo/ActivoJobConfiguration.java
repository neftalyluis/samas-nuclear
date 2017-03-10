/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.bootstraping.activo;

import java.io.File;
import java.io.IOException;
import mx.samas.domain.Activo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 *
 * Este Job se encarga de crear una lista por default de Activos que existen en
 * un CSV
 *
 * @author samas
 */
@Configuration
@EnableBatchProcessing
public class ActivoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job agregarActivosJob() {
        return jobs.get("agregarActivosJob")
                .start(agregarActivosStep())
                .build();
    }

    @Bean
    public Step agregarActivosStep() {
        return stepBuilderFactory.get("agregarActivosStep")
                .<Activo, Activo>chunk(1000)
                .reader(activoReader())
                .writer(activoWriter())
                .faultTolerant()
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Activo> activoReader() {
        FlatFileItemReader<Activo> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);//first line is title definition 
        reader.setResource(getFileFromDirectory());
        reader.setLineMapper(activoLineMapper());

        return reader;
    }

    private Resource getFileFromDirectory() {
        String PIP_LOCATION = "vector/pip/VectorTest.csv";
        ClassLoader classLoader = getClass().getClassLoader();

        File fl = new File(classLoader.getResource(PIP_LOCATION).getFile());

        return new FileSystemResource(fl);
    }

    @Bean
    public LineMapper<Activo> activoLineMapper() {
        DefaultLineMapper<Activo> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setQuoteCharacter('"');
        lineTokenizer.setStrict(true);

        BeanWrapperFieldSetMapper<Activo> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Activo.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(activoFieldSetMapper());

        return lineMapper;
    }

    @Bean
    public ActivoFieldSetMapper activoFieldSetMapper() {
        return new ActivoFieldSetMapper();
    }

    @Bean
    public ItemWriter<Activo> activoWriter() {
        return new ActivoItemWriter();
    }

}
