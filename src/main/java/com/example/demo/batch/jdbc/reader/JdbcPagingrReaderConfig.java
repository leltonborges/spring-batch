package com.example.demo.batch.jdbc.reader;

import com.example.demo.model.Client;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JdbcPagingrReaderConfig {
    @Bean("jdbcPagingItemReader")
    public JdbcPagingItemReader<Client> jdbcPagingItemReader(
            @Qualifier("projectDataSource") DataSource dataSource,
            @Qualifier("mySqlPagingQueryProvider") MySqlPagingQueryProvider pagingQueryProvider,
            @Qualifier("clientRowMapper") RowMapper<Client> clientRowMapper) {
        return new JdbcPagingItemReaderBuilder<Client>()
                .name("jdbcPagingItemReader")
                .dataSource(dataSource)
                .queryProvider(pagingQueryProvider)
                .pageSize(2)
                .beanRowMapper(Client.class)
                .rowMapper(clientRowMapper)
                .build();
    }

    @Bean("mySqlPagingQueryProvider")
    public MySqlPagingQueryProvider mySqlPagingQueryProvider() {
        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
        Map<String, Order> orderById = new HashMap<>();
        orderById.put("sq_client", Order.ASCENDING);
        mySqlPagingQueryProvider.setSelectClause("sq_client, st_name, st_last_name, nu_age, st_email");
        mySqlPagingQueryProvider.setFromClause("project.TB_CLIENT");
        mySqlPagingQueryProvider.setSortKeys(orderById);

        return mySqlPagingQueryProvider;
    }
}
