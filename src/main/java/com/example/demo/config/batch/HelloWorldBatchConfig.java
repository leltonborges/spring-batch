package com.example.demo.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldBatchConfig
        extends AbstractJobConfig{

    @Bean("helloWorldJob")
    public Job job(@Qualifier("printHelloWorldStep") Step step) {
        return jobBuilderFactory.get("imprimeOlaJob")
                                .start(step)
                                .incrementer(new RunIdIncrementer())
                                .build();
    }
}
