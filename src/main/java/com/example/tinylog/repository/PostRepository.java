package com.example.tinylog.repository;

import com.example.tinylog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findByTitleContainingIgnoreCase(String query, Pageable pageable);
}
