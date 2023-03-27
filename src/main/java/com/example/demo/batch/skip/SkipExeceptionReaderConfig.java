package com.example.demo.batch.skip;

import com.example.demo.model.Client;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;

@Configuration
public class SkipExeceptionReaderConfig {

    @Bean("skipExceptionReaderClient")
    public ItemReader<Client> skipExceptionReader(
            @Qualifier("projectDataSource") DataSource dataSource,
            @Qualifier("mySqlPagingQueryProvider") MySqlPagingQueryProvider pagingQueryProvider,
            @Qualifier("clientSkipRowMapper") RowMapper<Client> clientRowMapper) {
        return new JdbcPagingItemReaderBuilder<Client>()
                .name("skipExceptionReaderClient")
                .dataSource(dataSource)
                .queryProvider(pagingQueryProvider)
                .pageSize(2)
                .beanRowMapper(Client.class)
                .rowMapper(clientRowMapper)
                .build();
    }
}
