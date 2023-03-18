package com.example.demo.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    //    @StepScope
    @Bean
    @StepScope
    public Tasklet getHelloWorld(@Value("#{jobParameters['name']}") String name) {
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("Hello World: " + name);
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        return jobBuilderFactory.get("imprimeOlaJob")
                                .start(printHelloStep(stepBuilderFactory))
                                .incrementer(new RunIdIncrementer())
                                .build();
    }

    @Bean
    public Step printHelloStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("imprimeHelloStep")
                                 .tasklet(getHelloWorld(null)).build();
    }
}
