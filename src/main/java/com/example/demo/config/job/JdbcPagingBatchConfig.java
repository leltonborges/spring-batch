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
public class JdbcPagingBatchConfig
        extends JobAbstractConfig {

    @Autowired
    public JdbcPagingBatchConfig(JobBuilderFactory jobBuilderFactory) {
        super(jobBuilderFactory);
    }

    @Bean("jdbcPagingJob")
    public Job job(@Qualifier("jdbcPagingStep") Step step) {
        return jobBuilderFactory.get("jdbcPagingJob")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
