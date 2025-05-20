package com.francisconicolau.prueba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApiApplication {
    private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) {
        log.info("Inicio de la app");
        SpringApplication.run(ApiApplication.class, args);
    }

}
