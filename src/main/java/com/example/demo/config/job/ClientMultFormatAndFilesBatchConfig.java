package com.example.demo.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ClientMultFormatAndFilesBatchConfig {
    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    public ClientMultFormatAndFilesBatchConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean("clientMultFormantAndFilesJob")
    public Job clientMultFormantAndFilesJob(@Qualifier("clientMultFormatAndFilesStep") Step step) {
        return jobBuilderFactory.get("clientMultFormantAndFilesJob")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
