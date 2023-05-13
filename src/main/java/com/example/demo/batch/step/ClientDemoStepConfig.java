package com.example.demo.batch.step;

import com.example.demo.model.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientDemoStepConfig
        extends StepAbstractConfig {

    @Autowired
    protected ClientDemoStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("clientDemoStep")
    public Step clientDemoStep(@Qualifier("clientDemoReader") ItemReader<Client> clientItemReader,
                               @Qualifier("clientDemoWriter") ItemWriter<Client> clientItemWriter) {
        return this.stepBuilderFactory.get("clientDemoStep")
                .<Client, Client>chunk(4)
                .reader(clientItemReader)
                .writer(clientItemWriter)
                .build();
    }


}
