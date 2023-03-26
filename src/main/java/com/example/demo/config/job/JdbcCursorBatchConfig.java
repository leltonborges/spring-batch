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
public class JdbcCursorBatchConfig
        extends JobAbstractConfig {

    @Autowired
    public JdbcCursorBatchConfig(JobBuilderFactory jobBuilderFactory) {
        super(jobBuilderFactory);
    }

    @Bean("jdbcCursorJob")
    public Job job(@Qualifier("jdbcCursorStep") Step step) {
        return jobBuilderFactory.get("jdbcCursorJob")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
