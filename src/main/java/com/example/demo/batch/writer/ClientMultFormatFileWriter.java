package com.example.demo.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("clientMultFormatFileWriter")
public class ClientMultFormatFileWriter implements ItemWriter {
    @Override
    public void write(List items) throws Exception {
        items.forEach(System.out::println);
    }
}
