/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import mx.samas.domain.Activo;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.dto.VectorActivoDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author samas
 */
@Configuration
public class ValuacionJobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory emFactory;

    @Bean
    public Job valuacionJob() {
        return jobs.get("valuacionJob")
                .start(vectorActivoStep())
                .next(vectorPropiedadesStep())
                //                .next(propiedadesTerminosPIPStep())
                //                .next(propiedadesUsuarioCSVStep())
                //                .next(propiedadesJuliaStep())
                .build();
    }

    @Bean
    public Step vectorActivoStep() {
        return stepBuilderFactory.get("vectorActivoStep")
                .<VectorActivoDTO, VectorActivoDTO>chunk(1000)
                .reader(vectorActivoReader())
                .writer(vectorActivoWriter())
                .faultTolerant()
                .build();
    }

    @Bean
    public Step vectorPropiedadesStep() {
        return stepBuilderFactory.get("vectorPropiedadesStep")
                .<Activo, VectorActivo>chunk(1000)
                .reader(vectorPropiedadesReader())
                .processor(vectorPropiedadesProcessor())
                .writer(vectorPropiedadesWriter())
                .build();
    }

    @Bean(destroyMethod = "")
    @StepScope
    public JpaPagingItemReader<Activo> vectorPropiedadesReader() {
        JpaPagingItemReader<Activo> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emFactory);
        reader.setQueryString("SELECT a FROM Activo a JOIN FETCH a.propiedades p ");
        return reader;
    }

    @StepScope
    public FlatFileItemReader<VectorActivoDTO> vectorActivoReader() {
        FlatFileItemReader<VectorActivoDTO> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);//first line is title definition 
        reader.setResource(getFileFromDirectory("memes"));
        reader.setLineMapper(vectorActivoLineMapper());
        return reader;
    }

    //aqui leo
    @StepScope
    @Bean
    public Resource getFileFromDirectory(@Value("#{jobParameters[archivo]}") String fl) {
        return new FileSystemResource(fl);
    }
    
    @StepScope
    @Bean
    public Date getDateForJob(@Value("#{jobParameters[fecha]}") Date fecha) {
        return fecha;
    }

    @Bean
    public LineMapper<VectorActivoDTO> vectorActivoLineMapper() {
        DefaultLineMapper<VectorActivoDTO> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setQuoteCharacter('"');
        lineTokenizer.setStrict(true);

        BeanWrapperFieldSetMapper<VectorActivoDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(VectorActivoDTO.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(vectorActivoFieldSetMapper());

        return lineMapper;
    }

    @Bean
    public VectorActivoFieldSetMapper vectorActivoFieldSetMapper() {
        return new VectorActivoFieldSetMapper(new Date());
    }

    @Bean
    public ItemProcessor<VectorActivo, VectorActivo> vectorActivoProcessor() throws IOException {
        return new VectorActivoItemProcessor();
    }

    @Bean
    public ItemWriter<VectorActivoDTO> vectorActivoWriter() {
        return new VectorActivoItemWriter();
    }

    @Bean
    public ItemProcessor<Activo, VectorActivo> vectorPropiedadesProcessor() {
        return new VectorPropiedadesItemProcessor();
    }

    @Bean
    public JpaItemWriter<VectorActivo> vectorPropiedadesWriter() {
        JpaItemWriter<VectorActivo> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emFactory);
        return writer;
    }
}
