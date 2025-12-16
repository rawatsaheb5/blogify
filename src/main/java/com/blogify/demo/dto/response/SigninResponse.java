package com.blogify.demo.dto.response;

import lombok.Data;

@Data
public class SigninResponse {
    private String id;
    private String email;
    private String token;
}
