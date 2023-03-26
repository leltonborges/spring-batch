package com.example.demo.batch.jdbc.reader;

import com.example.demo.model.Client;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import javax.sql.DataSource;

@Configuration
public class JdbcCursorReaderConfig {

    @Bean("clientJdbcCursorItemReader")
    public JdbcCursorItemReader<Client> clientJdbcCursorItemReader(
            @Qualifier("projectDataSource") DataSource dataSource
            ){
        return new JdbcCursorItemReaderBuilder<Client>()
                .name("clientJdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select sq_client as id, st_name as name, st_last_name as lastName, nu_age as age, st_email as email from TB_CLIENT c")
                .beanRowMapper(Client.class)
                .rowMapper(new BeanPropertyRowMapper<>(Client.class))
                .build();
    }
}
