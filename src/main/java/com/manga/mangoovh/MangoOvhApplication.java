package com.manga.mangoovh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@SpringBootApplication
public class MangoOvhApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoOvhApplication.class, args);
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.manga.mangoovh.model")
                .persistenceUnit("mangoovhPU")
                .build();
    }
}
