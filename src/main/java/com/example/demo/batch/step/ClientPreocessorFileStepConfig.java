package com.example.demo.batch.step;

import com.example.demo.model.ClientProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientPreocessorFileStepConfig
        extends StepAbstractConfig {

    @Autowired
    protected ClientPreocessorFileStepConfig(StepBuilderFactory stepBuilderFactory) {
        super(stepBuilderFactory);
    }

    @Bean("clientPreocessorFileStep")
    public Step clientDelimitedFileStep(
            @Qualifier("clientProcessorFileItemReader") ItemReader<ClientProcessor> clientProcessorItemReader,
            @Qualifier("clientProcessorFileWriter") ItemWriter<ClientProcessor> processorItemWriter,
//            @Qualifier("scriptItemProcessorClient") ItemProcessor<ClientProcessor, ClientProcessor> scriptItemProcessor,
            @Qualifier("clientItemProcessor") ItemProcessor<ClientProcessor, ClientProcessor> itemProcessor) {
        return stepBuilderFactory.get("clientPreocessorFileStep")
                                 .<ClientProcessor, ClientProcessor>chunk(1)
                                 .reader(clientProcessorItemReader)
                                 .processor(itemProcessor)
//                                 .processor(scriptItemProcessor)
                                 .writer(processorItemWriter)
                                 .build();

    }
}
