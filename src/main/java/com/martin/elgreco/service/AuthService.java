package com.martin.elgreco.service;

import com.martin.elgreco.domain.AdminUser;
import com.martin.elgreco.dto.auth.LoginRequest;
import com.martin.elgreco.dto.auth.LoginResponse;
import com.martin.elgreco.repository.AdminUserRepository;
import com.martin.elgreco.security.JwtService;

import java.time.LocalDateTime;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final AdminUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            AdminUserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        AdminUser admin = repository.findByUsername(request.username())
                .filter(AdminUser::getActivo)
                .filter(user -> passwordEncoder.matches(request.password(), user.getPasswordHash()))
                .orElseThrow(() -> new BadCredentialsException("Usuario o contraseña incorrectos"));
        admin.setUltimoAcceso(LocalDateTime.now());
        repository.save(admin);
        return new LoginResponse(jwtService.generateToken(admin.getUsername()), "Bearer");
    }
}
