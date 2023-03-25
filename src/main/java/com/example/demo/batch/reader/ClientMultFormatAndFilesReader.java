package com.example.demo.batch.reader;

import com.example.demo.model.Domain;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ClientMultFormatAndFilesReader {
    @Bean("clientMultFormatAndFilesItemReader")
    @StepScope
    public MultiResourceItemReader<Domain> clientMultFormatAndFilesItemReader(
            @Value("#{jobParameters['multFormatAndFilesNameClient']}") Resource[] resources,
            @Qualifier("clientMultFormatFileItemReader") FlatFileItemReader<Domain> flatFileItemReader) {

        return new MultiResourceItemReaderBuilder<Domain>()
                .name("clientMultFormatFileItemReader")
                .resources(resources)
                .delegate(new ClientByTransactionReader(flatFileItemReader))
                .build();
    }


}
