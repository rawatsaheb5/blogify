package com.blogify.demo.service;

import java.util.List;

import com.blogify.demo.dto.request.CreatePostRequest;
import com.blogify.demo.dto.response.PostResponse;

public interface PostService {
    PostResponse createPost(CreatePostRequest postRequest, String userEmail);

    List<PostResponse> getAllPosts();

    List<PostResponse> getAllUserPosts(String email);

    PostResponse getPostById(Long id);
}