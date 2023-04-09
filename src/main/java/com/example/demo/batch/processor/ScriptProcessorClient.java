package com.example.demo.batch.processor;

import com.example.demo.model.ClientProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScriptProcessorClient {
    @Bean
    public ItemProcessor<ClientProcessor, ClientProcessor> scriptItemProcessorClient(){
        return new ScriptItemProcessorBuilder<ClientProcessor, ClientProcessor>()
                .language("nashorn")
                .scriptSource("""
                        var email = item.getEmail;
                        var fileExist = `ls | grep ${email}.txt`;
                        if(!fileExist) item; else null;
                        """).build();
    }
}
