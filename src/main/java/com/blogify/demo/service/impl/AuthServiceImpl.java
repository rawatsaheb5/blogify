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

        if (userRepository.ExistsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userRepository.save(user);

        return new SignupResponse(savedUser.getId(), savedUser.getEmail());
    }
}
