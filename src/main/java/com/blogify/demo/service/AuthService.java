package com.blogify.demo.service;

import com.blogify.demo.dto.request.SigninRequest;
import com.blogify.demo.dto.request.SignupRequest;
import com.blogify.demo.dto.response.SigninResponse;
import com.blogify.demo.dto.response.SignupResponse;

public interface AuthService {

    SignupResponse signup(SignupRequest request);

    SigninResponse signin(SigninRequest request);
}
