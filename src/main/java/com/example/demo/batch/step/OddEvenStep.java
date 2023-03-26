package com.example.demo.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OddEvenStep
        extends StepAbstractConfig {

    @Autowired
    protected OddEvenStep(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("printEvenOddStep")
    public Step printEvenOdd(@Qualifier("oddEvenReader") IteratorItemReader<Integer> tenReader,
                             @Qualifier("oddEvenProcessor") ItemProcessor<Integer, String> oddOrEvenProcessor,
                             @Qualifier("OddEvenWriter") ItemWriter<String> printWriter) {
        return this.stepBuilderFactory.get("PrintEvenOdd")
                                      .<Integer, String>chunk(1)
                                      .reader(tenReader)
                                      .processor(oddOrEvenProcessor)
                                      .writer(printWriter)
                                      .build();
    }
}
