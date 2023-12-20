package com.javarush.dragonapp;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@Transactional
@Sql({"classpath:sql/dml.sql"})
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class BaseTest {

    private static final PostgreSQLContainer<?> container
            =  new PostgreSQLContainer<>("postgres:16.1");

    @BeforeAll
    static void runContainer(){
        container.start();
    }

    @DynamicPropertySource
    static void propertyContainer(DynamicPropertyRegistry propertyRegistry){
        propertyRegistry.add("spring.datasource.url", container::getJdbcUrl);
    }


}
