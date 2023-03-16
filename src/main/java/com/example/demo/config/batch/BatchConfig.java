package com.example.demo.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        return jobBuilderFactory.get("imprimeOlaJob")
                                .start(printHelloStep(stepBuilderFactory))
                                .build();
    }

    @Bean
    public Step printHelloStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("imprimeHelloStep")
                                 .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
                                     System.out.println("Hello World");
                                     return RepeatStatus.FINISHED;
                                 }).build();
    }
}
