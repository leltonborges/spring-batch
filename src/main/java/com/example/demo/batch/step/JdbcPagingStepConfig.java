package com.example.demo.batch.step;


import com.example.demo.model.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPagingStepConfig
        extends StepAbstractConfig{

    @Autowired
    protected JdbcPagingStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("jdbcPagingStep")
    public Step jdbcPagingStep(
            @Qualifier("jdbcPagingItemReader") JdbcPagingItemReader<Client> clientJdbcPagingItemReader,
            @Qualifier("clientRepositoryItemReader")JpaPagingItemReader<Client> clientJpaPagingItemReader,
            @Qualifier("jdbcPagingWriter") ItemWriter<Client> clientItemWriter
            ){
        return stepBuilderFactory.get("jdbcPagingStep")
                .<Client, Client>chunk(2)
//                .reader(clientJdbcPagingItemReader)
                .reader(clientJpaPagingItemReader)
                .writer(clientItemWriter)
                .build();

    }
}
