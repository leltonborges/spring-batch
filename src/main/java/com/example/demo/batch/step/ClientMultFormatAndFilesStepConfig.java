package com.example.demo.batch.step;

import com.example.demo.model.Domain;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ClientMultFormatAndFilesStepConfig {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ClientMultFormatAndFilesStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("clientMultFormatAndFilesStep")
    public Step clientMultFormatFileStep(
            @Qualifier("clientMultFormatAndFilesItemReader") MultiResourceItemReader<Domain> clientItemReader,
            @Qualifier("clientMultFormatFileWriter") ItemWriter<Domain> clientItemWriter) {
        return this.stepBuilderFactory.get("clientMultFormatAndFilesStep")
                                      .<Domain, Domain>chunk(4)
                                      .reader(clientItemReader)
                                      .writer(clientItemWriter)
                                      .build();
    }
}
