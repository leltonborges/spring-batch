package com.example.demo.batch.writer;

import com.example.demo.model.ClientProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("clientProcessorFileWriter")
public class ClientProcessorFileWriter
        implements ItemWriter<ClientProcessor> {
    @Override
    public void write(List<? extends ClientProcessor> items){
        items.forEach(System.out::println);
    }
}
