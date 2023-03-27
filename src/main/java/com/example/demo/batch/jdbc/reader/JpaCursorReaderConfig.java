package com.example.demo.batch.jdbc.reader;

import com.example.demo.model.Client;
import com.example.demo.repository.project.ClientRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNamedQueryProvider;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaCursorReaderConfig {
    @Bean("clientJpaPagingItemReader")
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

    @Bean("clientRepositoryItemReader")
    public RepositoryItemReader<Client> clientRepositoryItemReader(ClientRepository clientRepository){
        Map<String, Sort.Direction> mapSort = new HashMap<>();
        RepositoryItemReader<Client> clientRepositoryItemReader = new RepositoryItemReader<>();

        mapSort.put("id", Sort.Direction.ASC);
        clientRepositoryItemReader.setRepository(clientRepository);
        clientRepositoryItemReader.setPageSize(2);
        clientRepositoryItemReader.setSort(mapSort);
        clientRepositoryItemReader.setMethodName("findAll");

        return clientRepositoryItemReader;
    }


}
