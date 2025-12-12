package com.blogify.demo.entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String description;

    private Long likeCount;

    private Long commentCount;

    private boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
