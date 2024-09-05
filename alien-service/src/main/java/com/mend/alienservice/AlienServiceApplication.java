package com.mend.alienservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.mend.alienservice.model")
public class AlienServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlienServiceApplication.class, args);
    }

}
