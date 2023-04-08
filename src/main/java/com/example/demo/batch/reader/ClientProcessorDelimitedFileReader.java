package com.example.demo.batch.reader;

import com.example.demo.model.ClientProcessor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ClientProcessorDelimitedFileReader {

    @Bean("clientProcessorFileItemReader")
    @StepScope
    public FlatFileItemReader<ClientProcessor> flatFileItemReader(@Value("#{jobParameters['fileNameClientProcess']}")Resource resource){
        return new FlatFileItemReaderBuilder<ClientProcessor>()
                .name("clientProcessorFileItemReader")
                .resource(resource)
                .delimited().names("name", "age", "email")
                .targetType(ClientProcessor.class)
                .build();
    }
}
