package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("oddEvenProcessor")
public class OddOrEvenProcessor
        implements ItemProcessor<Integer, String> {

    @Override
    public String process(Integer item) {
        return item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Impar", item);
    }
}
