package com.example.demo.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldStep
        extends StepAbstractConfig {

    @Autowired
    protected HelloWorldStep(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("printHelloWorldStep")
    public Step printHelloWorldStep(@Qualifier("helloWorldTasket") Tasklet helloWord) {
        return this.stepBuilderFactory.get("imprimeHelloWorldStep")
                                      .tasklet(helloWord)
                                      .build();
    }
}
