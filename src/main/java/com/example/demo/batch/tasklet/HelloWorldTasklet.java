package com.example.demo.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("helloWorldTasket")
public class HelloWorldTasklet
        implements Tasklet {

    @Bean("helloWorldStepTaskle")
    @StepScope
    public Tasklet getHelloWorld(@Value("#{jobParameters['name']}") String name) {
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("Hello World: " + name);
            return RepeatStatus.FINISHED;
        };
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        System.out.println("Hello World");
        return RepeatStatus.FINISHED;

    }
}
