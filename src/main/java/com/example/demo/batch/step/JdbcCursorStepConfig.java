package com.example.demo.batch.step;


import com.example.demo.model.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcCursorStepConfig
        extends StepAbstractConfig{

    @Autowired
    protected JdbcCursorStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("jdbcCursorStep")
    public Step jdbcCursorStep(
            @Qualifier("clientJdbcCursorItemReader") JdbcCursorItemReader<Client> clientJdbcCursorItemReader,
            @Qualifier("jdbcCursorWriter") ItemWriter<Client> clientItemWriter
            ){
        return stepBuilderFactory.get("jdbcCursorStep")
                .<Client, Client>chunk(1)
                .reader(clientJdbcCursorItemReader)
                .writer(clientItemWriter)
                .build();

    }
}
