package com.blogify.demo.service;

import com.blogify.demo.dto.request.CreatePostRequest;
import com.blogify.demo.dto.response.PostResponse;

public interface PostService {
    PostResponse createPost(CreatePostRequest postRequest, String userEmail);
}