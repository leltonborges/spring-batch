package com.example.demo.batch.reader;

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
    @Bean
    @StepScope
    public FlatFileItemReader clientMultFormatFileItemReader(@Value("#{jobParameters['multFormatFileNameClient']}")Resource resource,
                                                             @Qualifier("patternMatchingCompositeLineMapper") LineMapper lineMapper){
        return new FlatFileItemReaderBuilder()
                .name("clientMultFormatFileItemReader")
                .resource(resource)
                .lineMapper(lineMapper)
                .build();
    }


}
