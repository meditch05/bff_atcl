package com.mw.mwportal.bff.config;

// Need DataSourceProperties.java

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mwportalEntityManagerFactory", 
        transactionManagerRef = "mwportalTransactionManager", 
        basePackages = { "com.mw.mwportal.bff.repository" })
public class MwportalDataSourceConfig {
    
    @Autowired
	@Qualifier("mwportalDataSource")    
    private DataSource mwportalDataSource;
    
    @Primary
    @Bean(name = "mwportalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mwportalEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mwportalDataSource)
                .packages("com.mw.mwportal.bff.entity")
                .persistenceUnit("mwportal")
                .build();
    }
    
    @Primary
    @Bean("mwportalTransactionManager")
    public PlatformTransactionManager mwportalTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(mwportalEntityManagerFactory(builder).getObject());
    }

}