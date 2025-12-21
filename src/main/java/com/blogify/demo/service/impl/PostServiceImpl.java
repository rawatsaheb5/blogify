package com.blogify.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blogify.demo.dto.request.CreatePostRequest;
import com.blogify.demo.dto.response.PostResponse;
import com.blogify.demo.entity.PostEntity;
import com.blogify.demo.entity.UserEntity;
import com.blogify.demo.exception.ApiException;
import com.blogify.demo.repository.PostRepository;
import com.blogify.demo.repository.UserRepository;
import com.blogify.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,
            UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponse createPost(CreatePostRequest request, String userEmail) {

        UserEntity author = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ApiException("User not found"));

        PostEntity post = new PostEntity();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setAuthor(author);
        post.setLikeCount(0L);
        post.setCommentCount(0L);
        post.setPublished(false);
        post.setCreatedAt(LocalDateTime.now());

        PostEntity savedPost = postRepository.save(post);

        return new PostResponse(
                savedPost.getId().toString(),
                savedPost.getTitle(),
                savedPost.getDescription(),
                savedPost.getAuthor().getEmail(), // ✅ author in correct place
                savedPost.getLikeCount(),
                savedPost.getCommentCount(),
                savedPost.isPublished(),
                savedPost.getCreatedAt());
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());

    }

    private PostResponse mapToResponse(PostEntity post) {
        return new PostResponse(
                post.getId().toString(),
                post.getTitle(),
                post.getDescription(),
                post.getAuthor().getEmail(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.isPublished(),
                post.getCreatedAt());
    }

    @Override
    public List<PostResponse> getAllUserPosts(String email) {

        List<PostEntity> userPosts = postRepository.findByAuthorEmail(email);

        return userPosts.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }
    
    @Override
    public PostResponse getPostById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ApiException("Post not found"));
        return mapToResponse(post);
    }
}
