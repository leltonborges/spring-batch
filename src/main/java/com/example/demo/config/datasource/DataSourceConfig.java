package com.example.demo.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("batchDataSource")
    public DataSource batchDataSource(@Qualifier("batchDataSourceProperties") DataSourceProperties batchDataSourceProperties) {
        return batchDataSourceProperties.initializeDataSourceBuilder()
                                        .build();
    }

    @Bean("h2DataSource")
    public DataSource h2DataSource(@Qualifier("h2DataSourceProperties") DataSourceProperties h2DataSourceProperties) {
        return h2DataSourceProperties.initializeDataSourceBuilder()
                                     .build();
    }
}
