package com.example.demo.batch.reader;

import com.example.demo.model.Domain;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ClientMultFormatFileReader {
    @Bean("clientMultFormatFileItemReader")
    @StepScope
    public FlatFileItemReader<Domain> clientMultFormatFileItemReader(
            @Value("#{jobParameters['multFormatFileNameClient']}") Resource resource,
            @Qualifier("patternMatchingCompositeLineMapper") LineMapper<Domain> lineMapper) {
        return new FlatFileItemReaderBuilder<Domain>()
                .name("clientMultFormatFileItemReader")
                .resource(resource)
                .lineMapper(lineMapper)
                .build();
    }


}
