package com.jabiz.erp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.hikari.data-source-class-name}")
    private String DRIVER_CLASS_NAME;
    @Value("${spring.datasource.hikari.jdbc-url}")
    private String JDBC_URL;
    @Value("${spring.datasource.hikari.username}")
    private String USER_NAME;
    @Value("${spring.datasource.hikari.password}")
    private String PASSWORD;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DRIVER_CLASS_NAME);
        hikariConfig.setJdbcUrl(JDBC_URL);
        hikariConfig.setUsername(USER_NAME);
        hikariConfig.setPassword(PASSWORD);

        return hikariConfig;
    }

    @Bean
    public DataSource dataSource() throws Exception {
        DataSource dataSource = new HikariDataSource(hikariConfig());

        return dataSource;
    }

}
