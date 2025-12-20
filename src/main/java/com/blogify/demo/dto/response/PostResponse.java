package com.blogify.demo.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse {
    
    public String postId;
    public String title;
    public String description;
    public String author;
    public Long likeCount;
    public Long commentCount;
    public boolean isPublished;
    public LocalDateTime createdAt;
}
