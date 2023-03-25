package com.example.demo.batch.writer;

import com.example.demo.model.Domain;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("clientMultFormatFileWriter")
public class ClientMultFormatFileWriter implements ItemWriter<Domain> {
    @Override
    public void write(List<? extends Domain> items) {
        items.forEach(System.out::println);
    }
}
