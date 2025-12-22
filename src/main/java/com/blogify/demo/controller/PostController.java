package com.blogify.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.blogify.demo.dto.request.CreatePostRequest;
import com.blogify.demo.dto.request.UpdatePostRequest;
import com.blogify.demo.dto.response.PostResponse;
import com.blogify.demo.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


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

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("")
    public ResponseEntity<List<PostResponse>> getAllUserPosts() {

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return ResponseEntity.ok(postService.getAllUserPosts(email));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePostById(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        PostResponse response = postService.updatePostById(postId, request, email);
        return ResponseEntity.ok(response);
    }

}