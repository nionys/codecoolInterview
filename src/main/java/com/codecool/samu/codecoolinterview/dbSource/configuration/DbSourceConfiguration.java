package com.codecool.samu.codecoolinterview.dbSource.configuration;

import com.codecool.samu.codecoolinterview.dbSource.model.JsonRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "sourceEntityManagerFactory",
    transactionManagerRef = "sourceTransactionManager",
    basePackages = "com.codecool.samu.codecoolinterview.dbSource.repository"
)
public class DbSourceConfiguration {
    @Bean(name = "db-source")
    @ConfigurationProperties("app.dbsource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(
        @Qualifier("db-source") DataSource dataSource,
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(dataSource)
            .packages(JsonRecord.class)
            .build();
    }

    @Bean
    public PlatformTransactionManager sourceTransactionManager(
        @Qualifier("sourceEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
