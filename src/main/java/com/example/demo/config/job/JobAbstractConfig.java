package com.example.demo.config.job;

import lombok.Getter;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;


@Getter
@EnableBatchProcessing
public abstract class JobAbstractConfig {
    protected final JobBuilderFactory jobBuilderFactory;

    protected JobAbstractConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }
}
