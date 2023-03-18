package com.example.demo.reader;

import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component("oddEvenReader")
public class OddEvenReader
        extends IteratorItemReader<Integer> {
    public OddEvenReader() {
        super(IntStream.rangeClosed(1, 10)
                       .<List<Integer>>collect(ArrayList::new, List::add, List::addAll)
                       .iterator());
    }
}
