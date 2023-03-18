package com.example.demo.batch.step;

import com.example.demo.model.Client;
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
public class ClientFileStep {
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ClientFileStep(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("clientFlatFileStep")
    public Step clientFlatFileStep(@Qualifier("clientFlatFileItermReader") ItemReader<Client> clientItemReader,
                                    @Qualifier("clientFlatFileWriter") ItemWriter<Client> clientItemWriter) {
        return this.stepBuilderFactory.get("imprimeHelloWorldStep")
                                      .<Client, Client>chunk(1)
                                      .reader(clientItemReader)
                                      .writer(clientItemWriter)
                                      .build();
    }
}
