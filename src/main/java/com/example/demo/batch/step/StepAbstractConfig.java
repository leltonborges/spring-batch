package com.example.demo.batch.step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

@EnableBatchProcessing
public abstract class StepAbstractConfig {
    protected final StepBuilderFactory stepBuilderFactory;

    protected StepAbstractConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }
}
