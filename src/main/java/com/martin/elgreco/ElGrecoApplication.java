package com.martin.elgreco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class ElGrecoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElGrecoApplication.class, args);
    }
}