package com.example.demo.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("FirtPrintWriter")
public class FirstPrintWriter implements ItemWriter<String>{

    @Override
    public void write(List<? extends String> items){
        items.forEach(System.out::println);
    }
}
