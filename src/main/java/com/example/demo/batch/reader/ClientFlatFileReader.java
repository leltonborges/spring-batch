package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ClientFlatFileReader {

    @Bean("clientFlatFileItermReader")
    @StepScope
    public FlatFileItemReader<Client> clientFlatFileItemReader(@Value("#{jobParameters['fileNameClient']}") Resource resource) {
        return new FlatFileItemReaderBuilder<Client>()
                .name("clientFlatFileItermReader")
                .resource(resource)
                .fixedLength()
                .columns(new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43))
                .names("name", "lasName", "age", "email")
                .targetType(Client.class)
                .build();
    }
}
