package com.lasias.review_service.services;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.mysql.MySQLContainer;

public abstract class MySqlDatabaseConfig {

    static final MySQLContainer mysql = new MySQLContainer("mysql:8.0")
            .withDatabaseName("review_db_test")
            .withUsername("tester")
            .withPassword("tester");

    static{
        mysql.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }
}
