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
public class JdbcSkipPagingStepConfig
        extends StepAbstractConfig{

    @Autowired
    protected JdbcSkipPagingStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("jdbcSkipPagingStep")
    public Step jdbcPagingStep(
            @Qualifier("skipExceptionReaderClient") ItemReader<Client> clientItemReader,
            @Qualifier("jdbcPagingWriter") ItemWriter<Client> clientItemWriter
            ){
        return stepBuilderFactory.get("jdbcSkipPagingStep")
                .<Client, Client>chunk(2)
                .reader(clientItemReader)
                .writer(clientItemWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(2)
                .build();

    }
}
