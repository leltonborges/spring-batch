package com.example.demo.config.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("OddEvenBatchConfig")
public class OddEvenBatchConfig
        extends JobAbstractConfig {

    @Autowired
    public OddEvenBatchConfig(JobBuilderFactory jobBuilderFactory) {
        super(jobBuilderFactory);
    }

    @Bean("OddEvenJob")
    public Job job(@Qualifier("printEvenOddStep") Step step) {
        return jobBuilderFactory.get("imprimeOddEven")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
