package com.mw.mwportal.bff.properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties
public class DataSourceProperties {
    
    @Bean(name = "mwportalDataSource")
    @Qualifier("mwportalDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.mwportal")
    public DataSource mwportalDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    
    @Bean(name = "opmatenodeDataSource")
    @Qualifier("opmatenodeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.opmatenode")
    public DataSource opmatenodeDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}