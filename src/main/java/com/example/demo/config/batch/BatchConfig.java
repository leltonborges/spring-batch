package com.example.demo.config.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    //    @StepScope
    @Bean
    @StepScope
    public Tasklet getHelloWorld(@Value("#{jobParameters['name']}") String name) {
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            System.out.println("Hello World: " + name);
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        return jobBuilderFactory.get("imprimeOlaJob")
//                                .start(printHelloStep(stepBuilderFactory))
                                .start(printEvenOdd(stepBuilderFactory))
                                .incrementer(new RunIdIncrementer())
                                .build();
    }

    @Bean
    public Step printHelloStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("imprimeHelloStep")
                                 .tasklet(getHelloWorld(null)).build();
    }

    public Step printEvenOdd(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("PrintEvenOdd")
                                 .<Integer, String>chunk(1)
                                 .reader(countTenReader())
                                 .processor(oddOrEvenProcessor())
                                 .writer(printWriter())
                                 .build();
    }


    private IteratorItemReader<Integer> countTenReader() {
        List<Integer> nums = IntStream.rangeClosed(1, 10).boxed().toList();
        return new IteratorItemReader<>(nums);
    }

    private FunctionItemProcessor<Integer, String> oddOrEvenProcessor() {
        return new FunctionItemProcessor<>(i -> i % 2 == 0 ? String.format("Item %s é Par", i) : String.format("Item %s é Impar", i));
    }

    private ItemWriter<String> printWriter() {
        return i -> i.forEach(System.out::println);
    }


}
