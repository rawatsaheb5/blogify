package com.blogify.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.blogify.demo.dto.request.CreatePostRequest;
import com.blogify.demo.dto.response.PostResponse;
import com.blogify.demo.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(
            @RequestBody CreatePostRequest request) {

                // accessing email from token
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        PostResponse response = postService.createPost(request, email);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}