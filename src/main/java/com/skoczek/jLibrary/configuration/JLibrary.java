package com.skoczek.jLibrary.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.skoczek.jLibrary")
@EnableJpaRepositories(basePackages = "com.skoczek.jLibrary.repository")
@EntityScan(basePackages = "com.skoczek.jLibrary.domain")
public class JLibrary extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JLibrary.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

}

