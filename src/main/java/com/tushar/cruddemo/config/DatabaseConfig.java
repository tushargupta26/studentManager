package com.tushar.cruddemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver ");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/student_tracker");
        dataSource.setUsername( "springstudent" );
        dataSource.setPassword( "springstudent" );
        return dataSource;
    }
}
