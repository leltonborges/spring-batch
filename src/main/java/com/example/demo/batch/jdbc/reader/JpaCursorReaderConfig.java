package com.example.demo.batch.jdbc.reader;

import com.example.demo.model.Client;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNamedQueryProvider;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class JpaCursorReaderConfig {
    @Bean("clientRepositoryItemReader")
    public JpaPagingItemReader<Client> clientJpaPagingItemReader(
            EntityManagerFactory entityManagerFactory,
            JpaQueryProvider jpaQueryProvider) {
        JpaPagingItemReader<Client> clientJpaPagingItemReader = new JpaPagingItemReader<>();
        clientJpaPagingItemReader.setPageSize(2);
        clientJpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        clientJpaPagingItemReader.setQueryProvider(jpaQueryProvider);
        return clientJpaPagingItemReader;
    }

    @Bean
    public JpaQueryProvider jpaQueryProvider(EntityManager entityManager){
        JpaNamedQueryProvider<Client> jpaNamedQueryProvider = new JpaNamedQueryProvider<>();
        jpaNamedQueryProvider.setEntityClass(Client.class);
        jpaNamedQueryProvider.setEntityManager(entityManager);
        jpaNamedQueryProvider.setNamedQuery("findAllSort");
        return jpaNamedQueryProvider;
    }

}
