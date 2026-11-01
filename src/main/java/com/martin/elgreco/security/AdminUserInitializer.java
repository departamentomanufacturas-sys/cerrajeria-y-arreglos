package com.martin.elgreco.security;

import com.martin.elgreco.domain.AdminUser;
import com.martin.elgreco.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    @Bean
    ApplicationRunner createInitialAdmin(
            AdminUserRepository repository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.username:}") String username,
            @Value("${app.admin.password:}") String password) {
        return args -> {
            if (username.isBlank() && password.isBlank()) {
                return;
            }
            if (username.isBlank() || password.isBlank()) {
                throw new IllegalStateException("Configure ambos valores: app.admin.username y app.admin.password");
            }
            if (repository.findByUsername(username).isEmpty()) {
                repository.save(new AdminUser(username, passwordEncoder.encode(password)));
            }
        };
    }
}
