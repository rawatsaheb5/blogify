package com.blogify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogify.demo.entity.PostEntity;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    
    List<PostEntity> findByAuthorEmail(String email);
}