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
public class ClientDelimitedFileStepConfig
        extends StepAbstractConfig {

    @Autowired
    protected ClientDelimitedFileStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("clientDelimitedFileStep")
    public Step clientDelimitedFileStep(@Qualifier("clientDelimitedFileItemReader") ItemReader<Client> clientDelimitedItemReader,
                                        @Qualifier("clientDelimitedFileWriter") ItemWriter<Client> clientDelimitedItemWriter) {
        return stepBuilderFactory.get("clientDelimitedFileStep")
                                 .<Client, Client>chunk(1)
                                 .reader(clientDelimitedItemReader)
                                 .writer(clientDelimitedItemWriter)
                                 .build();

    }
}
