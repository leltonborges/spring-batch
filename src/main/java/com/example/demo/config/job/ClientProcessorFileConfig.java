package com.example.demo.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProcessorFileConfig
        extends JobAbstractConfig {


    @Autowired
    protected ClientProcessorFileConfig(JobBuilderFactory jobBuilderFactory) {
        super(jobBuilderFactory);
    }

    @Bean("clientProcessorFileJob")
    public Job clientDelimitedFileJob(@Qualifier("clientPreocessorFileStep") Step step) {
        return jobBuilderFactory.get("clientProcessorFileJob")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();

    }
}
