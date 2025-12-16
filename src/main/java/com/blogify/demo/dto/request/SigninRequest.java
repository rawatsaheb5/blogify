package com.blogify.demo.dto.request;

import lombok.Data;

@Data
public class SigninRequest {

    private String email;
    private String password;
}
