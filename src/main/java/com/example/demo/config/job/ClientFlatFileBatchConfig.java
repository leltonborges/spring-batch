package com.example.demo.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ClientFlatFileBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    public ClientFlatFileBatchConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean("clientFileFlatJob")
    public Job clientFileFlatJob(@Qualifier("clientFlatFileStep") Step step) {
        return jobBuilderFactory.get("clientFileFlatJob")
                                .start(step)
//                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
