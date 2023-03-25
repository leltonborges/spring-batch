package com.example.demo.batch.reader;

import com.example.demo.model.Client;
import com.example.demo.model.Domain;
import com.example.demo.model.Transaction;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.classify.Classifier;
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

    private <T extends Domain> Map<String, FieldSetMapper<T>> fieldSetMapper() {
        Map<String, FieldSetMapper<T>> fieldSetMapperMap = new HashMap<>();
        fieldSetMapperMap.put("0*", (FieldSetMapper<T>) fieldSetMapper(Client.class));
        fieldSetMapperMap.put("1*", (FieldSetMapper<T>) fieldSetMapper(Transaction.class));
        return fieldSetMapperMap;
    }

    private <T extends Domain> FieldSetMapper<T> fieldSetMapper(Class<T> clasz) {
        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(clasz);
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
        lineTokenizer.setNames("id", "description", "amount");
        lineTokenizer.setIncludedFields(1, 2, 3);
        return lineTokenizer;
    }

    private LineTokenizer clientLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "lastName", "age", "email");
        lineTokenizer.setIncludedFields(1, 2, 3, 4);
        return lineTokenizer;
    }

    public interface ABCInterface<T> {
        T mapLine(String line, int lineNumber) throws Exception;
//        protected final DefaultLineMapper<T> defaultLineMapper;
//        protected final DelimitedLineTokenizer lineTokenizer;
//        protected final BeanWrapperFieldSetMapper<T> beanWrapperFieldSetMapper;
//
//        protected ABCInterface(DefaultLineMapper<T> defaultLineMapper, DelimitedLineTokenizer lineTokenizer, BeanWrapperFieldSetMapper<T> beanWrapperFieldSetMapper) {
//            this.defaultLineMapper = defaultLineMapper;
//            this.lineTokenizer = lineTokenizer;
//            this.beanWrapperFieldSetMapper = beanWrapperFieldSetMapper;
//        }
    }

    static class ABC
            implements LineMapper<Object> {
        private final Classifier<String, LineMapper<?>> classifier;

        public ABC(Classifier<String, LineMapper<?>> classifier) {
            this.classifier = classifier;
        }

        @Override
        public Object mapLine(String line, int lineNumber) throws Exception {
            return this.classifier.classify(line).mapLine(line, lineNumber);
        }
    }

    public static class TransactionLineMapper
            implements LineMapper<Transaction>, ABCInterface<Transaction> {
        private final DefaultLineMapper<Transaction> defaultLineMapper = new DefaultLineMapper<>();
        private final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Transaction> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();

        public TransactionLineMapper() {
            lineTokenizer.setNames("name", "lastName", "age", "email");
            lineTokenizer.setIncludedFields(1, 2, 3, 4);
            beanWrapperFieldSetMapper.setTargetType(Transaction.class);
            defaultLineMapper.setLineTokenizer(lineTokenizer);
            defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        }

        @Override
        public Transaction mapLine(String line, int lineNumber) throws Exception {
            return defaultLineMapper.mapLine(line, lineNumber);
        }
    }

    public static class ClientLineMapper
            implements LineMapper<Client>, ABCInterface<Client> {
        protected final DefaultLineMapper<Client> defaultLineMapper = new DefaultLineMapper<>();
        protected final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        protected final BeanWrapperFieldSetMapper<Client> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();

        public ClientLineMapper() {
            lineTokenizer.setNames("name", "lastName", "age", "email");
            lineTokenizer.setIncludedFields(1, 2, 3, 4);
            beanWrapperFieldSetMapper.setTargetType(Client.class);
            defaultLineMapper.setLineTokenizer(lineTokenizer);
            defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        }

        @Override
        public Client mapLine(String line, int lineNumber) throws Exception {
            return defaultLineMapper.mapLine(line, lineNumber);
        }
    }

    public static class ABCFacade {
        public LineMapper<ABCInterface> lineMapper(String classfiable) {
            return (String line, int lineNumber) -> {
                if (classfiable.startsWith("0")) return new ClientLineMapper();
                else return new TransactionLineMapper();
            };
        }
    }
}
