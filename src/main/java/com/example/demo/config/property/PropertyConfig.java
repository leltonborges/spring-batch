package com.example.demo.config.property;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PropertyConfig {
    @Primary
    @Bean("batchDataSourceProperties")
    @ConfigurationProperties("spring.datasource.batch")
    public DataSourceProperties batchDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("h2DataSourceProperties")
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties(){
        return new DataSourceProperties();
    }
}
