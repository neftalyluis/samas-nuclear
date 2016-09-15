/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.io.File;
import java.io.IOException;
import mx.samas.domain.VectorActivo;
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
 * @author samas
 */
@Configuration
@EnableBatchProcessing
public class ValuacionJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job valuacionJob() {
        return jobs.get("valuacionJob")
                .start(vectorActivoStep())
                //                .next(propiedadesVectorPIPStep())
                //                .next(propiedadesTerminosPIPStep())
                //                .next(propiedadesUsuarioCSVStep())
                //                .next(propiedadesJuliaStep())
                .build();
    }

    @Bean
    public Step vectorActivoStep() {
        return stepBuilderFactory.get("vectorActivoStep")
                .<VectorActivo, VectorActivo>chunk(1000)
                .reader(reader())
                .writer(writer())
                .faultTolerant()
                .build();
    }

//    @Bean
//    public Step propiedadesVectorPIPStep() {
//        return stepBuilderFactory.get("propiedadesVectorPIPStep")
//                .<VectorActivo, VectorActivo>chunk(1000)
//                .reader(reader())
//                .writer(writer())
//                .faultTolerant()
//                .build();
//    }
//
//    @Bean
//    public Step propiedadesTerminosPIPStep() {
//        return stepBuilderFactory.get("propiedadesTerminosPIPStep")
//                .<VectorActivo, VectorActivo>chunk(1000)
//                .reader(reader())
//                .writer(writer())
//                .faultTolerant()
//                .build();
//    }
//    @Bean
//    public Step propiedadesUsuarioCSVStep() {
//        return stepBuilderFactory.get("propiedadesUsuarioCSVStep")
//                .<VectorActivo, VectorActivo>chunk(1000)
//                .reader(reader())
//                .writer(writer())
//                .faultTolerant()
//                .build();
//    }
//
//    @Bean
//    public Step propiedadesJuliaStep() {
//        return stepBuilderFactory.get("propiedadesJuliaStep")
//                .<VectorActivo, VectorActivo>chunk(1000)
//                .reader(reader())
//                .writer(writer())
//                .faultTolerant()
//                .build();
//    }
    @Bean
    @StepScope
    public FlatFileItemReader<VectorActivo> reader() {
        FlatFileItemReader<VectorActivo> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);//first line is title definition 
        reader.setResource(getFileFromDirectory());
        reader.setLineMapper(lineMapper());

        return reader;
    }

    private Resource getFileFromDirectory() {
        String PIP_LOCATION = "vector/pip/VectorTest.csv";
        ClassLoader classLoader = getClass().getClassLoader();

        File fl = new File(classLoader.getResource(PIP_LOCATION).getFile());

        return new FileSystemResource(fl);
    }

    @Bean
    public LineMapper<VectorActivo> lineMapper() {
        DefaultLineMapper<VectorActivo> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setQuoteCharacter('"');
        lineTokenizer.setStrict(true);

        BeanWrapperFieldSetMapper<VectorActivo> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(VectorActivo.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(vectorActivoFieldSetMapper());

        return lineMapper;
    }

    @Bean
    public VectorActivoFieldSetMapper vectorActivoFieldSetMapper() {
        return new VectorActivoFieldSetMapper();
    }

    @Bean
    public ItemProcessor<VectorActivo, VectorActivo> processor() throws IOException {
        return new VectorActivoItemProcessor();
    }

    @Bean
    public ItemWriter<VectorActivo> writer() {
        return new VectorActivoItemWriter();
    }

}
