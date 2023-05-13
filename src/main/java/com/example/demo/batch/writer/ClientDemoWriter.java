package com.example.demo.batch.writer;

import com.example.demo.model.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDemoWriter implements ItemWriter<Client> {
    @Override
    public void write(List<? extends Client> items) {
        items.forEach(System.out::println);
    }
}
