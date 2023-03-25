package com.example.demo.batch.step;

import com.example.demo.batch.reader.ClientByTransactionReader;
import com.example.demo.model.Domain;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
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
    public Step clientMultFormatFileStep(@Qualifier("clientMultFormatFileItemReader") FlatFileItemReader<Domain> clientItemReader,
                                         @Qualifier("clientMultFormatFileWriter") ItemWriter<Domain> clientItemWriter) {
        return this.stepBuilderFactory.get("imprimeHelloWorldStep")
                                      .<Domain, Domain>chunk(4)
                                      .reader(new ClientByTransactionReader(clientItemReader))
                                      .writer(clientItemWriter)
                                      .build();
    }
}
