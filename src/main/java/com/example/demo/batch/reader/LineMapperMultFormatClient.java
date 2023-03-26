package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import com.example.demo.model.Domain;
import com.example.demo.model.Transaction;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LineMapperMultFormatClient {
    @Bean
    public PatternMatchingCompositeLineMapper<Domain> patternMatchingCompositeLineMapper() {
        PatternMatchingCompositeLineMapper<Domain> lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMapper());
        return lineMapper;
    }

    private Map<String, FieldSetMapper<Domain>> fieldSetMapper() {
        Map<String, FieldSetMapper<Domain>> fieldSetMapperMap = new HashMap<>();
        fieldSetMapperMap.put("0*", fieldSetMapper(Client.class));
        fieldSetMapperMap.put("1*", fieldSetMapper(Transaction.class));
        return fieldSetMapperMap;
    }

    private <T extends Domain> FieldSetMapper<Domain> fieldSetMapper(Class<T> clazz) {
        BeanWrapperFieldSetMapper<Domain> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(clazz);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizerMap = new HashMap<>();
        tokenizerMap.put("0*", clientLineTokenizer());
        tokenizerMap.put("1*", transactionTokenizer());
        return tokenizerMap;
    }

    private LineTokenizer transactionTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("idTransaction", "description", "amount");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private LineTokenizer clientLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "lastName", "age", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }
}
