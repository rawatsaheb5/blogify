package com.blogify.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogify.demo.entity.PostEntity;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    
    List<PostEntity> findByAuthorEmail(String email);
    Optional<PostEntity> findById(Long id);
}