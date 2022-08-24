package com.senla.service.custom;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(
        properties = "kafka.enable=false",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
public abstract class AbstractIntegrationTest {

    static PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:latest");

    static {
        DATABASE.start();
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DATABASE::getJdbcUrl);
        registry.add("spring.datasource.password", DATABASE::getPassword);
        registry.add("spring.datasource.username", DATABASE::getUsername);
    }
}
