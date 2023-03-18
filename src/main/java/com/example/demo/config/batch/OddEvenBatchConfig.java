package com.example.demo.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("OddEvenBatchConfig")
public class OddEvenBatchConfig
        extends AbstractJobConfig {
    @Bean("OddEvenJob")
    public Job job(@Qualifier("printEvenOddStep") Step step) {
        return jobBuilderFactory.get("imprimeOddEven")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
