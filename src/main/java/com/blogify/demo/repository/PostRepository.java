package com.blogify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogify.demo.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    
}