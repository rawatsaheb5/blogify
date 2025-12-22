package com.blogify.demo.dto.request;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private String title;
    private String description;
    private Boolean isPublished;
}
