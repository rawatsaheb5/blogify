package com.blogify.demo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogify.demo.dto.request.SignupRequest;
import com.blogify.demo.dto.response.SignupResponse;
import com.blogify.demo.entity.UserEntity;
import com.blogify.demo.repository.UserRepository;
import com.blogify.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignupResponse signup(SignupRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Generate username from email (part before @)
        String username = request.getEmail().split("@")[0];
        user.setUsername(username);

        UserEntity savedUser = userRepository.save(user);

        return new SignupResponse(savedUser.getId(), savedUser.getEmail());
    }
}
