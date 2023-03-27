package com.example.demo.batch.entitymanager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "projectEntityManager",
        transactionManagerRef = "projectTransactionManager",
        basePackages = {"com.example.demo.repository.project"}
)
public class EntityManagerFactoryConfig {
    @Bean("projectEntityManager")
    public LocalContainerEntityManagerFactoryBean managerFactoryBeanProject(
            EntityManagerFactoryBuilder builder,
            @Qualifier("projectDataSource") DataSource projectDataSource) {

        return builder.dataSource(projectDataSource)
                      .packages("com.example.demo.model")
                      .persistenceUnit("project_unit")
                      .build();
    }

    @Bean("projectTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("projectEntityManager") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
