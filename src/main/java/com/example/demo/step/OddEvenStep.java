package com.example.demo.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OddEvenStep
        extends AbstractStepConfig {

    @Bean("printEvenOddStep")
    public Step printEvenOdd(@Qualifier("oddEvenReader") IteratorItemReader<Integer> tenReader,
                             @Qualifier("oddEvenProcessor") ItemProcessor<Integer, String> oddOrEvenProcessor,
                             @Qualifier("FirtPrintWriter") ItemWriter<String> printWriter) {
        return this.stepBuilderFactory.get("PrintEvenOdd")
                                      .<Integer, String>chunk(1)
                                      .reader(tenReader)
                                      .processor(oddOrEvenProcessor)
                                      .writer(printWriter)
                                      .build();
    }
}
