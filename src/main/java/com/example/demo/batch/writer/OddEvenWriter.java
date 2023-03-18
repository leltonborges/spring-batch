package com.example.demo.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("OddEvenWriter")
public class OddEvenWriter
        implements ItemWriter<String>{

    @Override
    public void write(List<? extends String> items){
        items.forEach(System.out::println);
    }
}
