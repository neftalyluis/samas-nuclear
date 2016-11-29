/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.elastic.job;

import java.io.File;
import mx.samas.elastic.domain.VectorActivoPropiedadValor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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
 * @author samas
 */
@Configuration
public class PropiedadesJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job elasticJob() {
        return jobs.get("elasticJob")
                .start(elasticStep())
                .build();
    }

    @Bean
    public Step elasticStep() {
        return stepBuilderFactory.get("elasticStep")
                .<VectorActivoPropiedadValor, VectorActivoPropiedadValor>chunk(1000)
                .reader(activoElasticReader())
                .writer(activoElasticWriter())
                .faultTolerant()
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<VectorActivoPropiedadValor> activoElasticReader() {
        FlatFileItemReader<VectorActivoPropiedadValor> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);//first line is title definition 
        reader.setResource(getFileFromDirectory());
        reader.setLineMapper(activoElasticLineMapper());

        return reader;
    }

    private Resource getFileFromDirectory() {
        String PIP_LOCATION = "vector/pip/VectorTest.csv";
        ClassLoader classLoader = getClass().getClassLoader();

        File fl = new File(classLoader.getResource(PIP_LOCATION).getFile());

        return new FileSystemResource(fl);
    }

    @Bean
    public LineMapper<VectorActivoPropiedadValor> activoElasticLineMapper() {
        DefaultLineMapper<VectorActivoPropiedadValor> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setQuoteCharacter('"');
        lineTokenizer.setStrict(true);

        BeanWrapperFieldSetMapper<VectorActivoPropiedadValor> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(VectorActivoPropiedadValor.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(activoElasticFieldSetMapper());

        return lineMapper;
    }

    @Bean
    public VectorActivoElasticFieldSetMapper activoElasticFieldSetMapper() {
        return new VectorActivoElasticFieldSetMapper();
    }

    @Bean
    public ItemWriter<VectorActivoPropiedadValor> activoElasticWriter() {
        return new VectorActivoElasticItemWriter();
    }
}
