package com.example.demo.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ClientMultFormatFileStepConfig {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ClientMultFormatFileStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("clientMultFormatFileStep")
    public Step clientMultFormatFileStep(@Qualifier("clientMultFormatFileItemReader") ItemReader clientItemReader,
                                         @Qualifier("clientMultFormatFileWriter") ItemWriter clientItemWriter) {
        return this.stepBuilderFactory.get("imprimeHelloWorldStep")
                                      .chunk(4)
                                      .reader(clientItemReader)
                                      .writer(clientItemWriter)
                                      .build();
    }
}
