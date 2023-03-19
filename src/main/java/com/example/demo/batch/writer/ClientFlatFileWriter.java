package com.example.demo.batch.writer;

import com.example.demo.model.Client;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("clientFlatFileWriter")
public class ClientFlatFileWriter
        implements ItemWriter<Client> {
    @Override
    public void write(List<? extends Client> items) {
        items.forEach(i -> {
            if(i.getName().equals("Maria")) throw new RuntimeException("Error: Maria");
            else System.out.println(i);
        });
//        items.forEach(System.out::println);
    }
}
