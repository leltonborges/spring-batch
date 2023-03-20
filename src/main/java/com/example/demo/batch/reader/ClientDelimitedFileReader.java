package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ClientDelimitedFileReader {

    @Bean("clientDelimitedFileItemReader")
    @StepScope
    public FlatFileItemReader<Client> flatFileItemReader(@Value("#{jobParameters['delimitedFileNameClient']}")Resource resource){
        return new FlatFileItemReaderBuilder<Client>()
                .name("clientDelimitedFileItemReader")
                .resource(resource)
                .delimited().names("name", "lasName", "age", "email")
                .targetType(Client.class)
                .build();
    }
}
