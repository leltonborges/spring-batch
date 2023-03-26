package com.example.demo.batch.step;

import com.example.demo.model.Domain;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientMultFormatAndFilesStepConfig
        extends StepAbstractConfig {

    @Autowired
    protected ClientMultFormatAndFilesStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("clientMultFormatAndFilesStep")
    public Step clientMultFormatFileStep(
            @Qualifier("clientMultFormatAndFilesItemReader") MultiResourceItemReader<Domain> clientItemReader,
            @Qualifier("clientMultFormatFileWriter") ItemWriter<Domain> clientItemWriter) {
        return this.stepBuilderFactory.get("clientMultFormatAndFilesStep")
                                      .<Domain, Domain>chunk(4)
                                      .reader(clientItemReader)
                                      .writer(clientItemWriter)
                                      .build();
    }
}
