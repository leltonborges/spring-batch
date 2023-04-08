package com.example.demo.batch.processor;

import com.example.demo.model.ClientProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProcessadorValidationClient {

    @Bean("clientItemProcessor")
    public ItemProcessor<ClientProcessor, ClientProcessor> clientItemProcessor() {
        ValidatingItemProcessor<ClientProcessor> itemProcessor = new ValidatingItemProcessor<>();
        itemProcessor.setValidator(new ValidationClientProcessor());
        itemProcessor.setFilter(true);
        return itemProcessor;
    }

    private static class ValidationClientProcessor
            implements Validator<ClientProcessor> {
        private final Set<String> emails = new HashSet<>();

        @Override
        public void validate(ClientProcessor clientProcessor) throws ValidationException {
            if (emails.contains(clientProcessor.getEmail()))
                throw new ValidationException(String.format("The client %s is has processor", clientProcessor.getEmail()));
            emails.add(clientProcessor.getEmail());
        }
    }

}
