package com.example.demo.batch.processor;

import com.example.demo.model.ClientProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProcessadorValidationClient {
    private final Set<String> emails = new HashSet<>();

    @Bean("clientItemProcessor")
    public ItemProcessor<ClientProcessor, ClientProcessor> clientItemProcessor() throws Exception {
        return new CompositeItemProcessorBuilder<ClientProcessor, ClientProcessor>()
                .delegates(validationItemProcessor(), validatingItemProcessor())
                .build();
    }

    private BeanValidatingItemProcessor<ClientProcessor> validationItemProcessor() throws Exception {
        BeanValidatingItemProcessor<ClientProcessor> itemProcessor = new BeanValidatingItemProcessor<>();
        itemProcessor.setFilter(true);
        itemProcessor.afterPropertiesSet();
        return itemProcessor;
    }

    private ValidatingItemProcessor<ClientProcessor> validatingItemProcessor() {
        ValidatingItemProcessor<ClientProcessor> processor = new ValidatingItemProcessor<>();
        processor.setFilter(true);
        processor.setValidator(clientProcessorValidator());
        return processor;
    }

    private Validator<ClientProcessor> clientProcessorValidator() {
        return clientProcessor -> {
            if (emails.contains(clientProcessor.getEmail()))
                throw new ValidationException(String.format("The client of email: %s is has processor", clientProcessor.getEmail()));
            emails.add(clientProcessor.getEmail());
        };
    }
}
