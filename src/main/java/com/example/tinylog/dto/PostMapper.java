package com.example.tinylog.dto;

import com.example.tinylog.domain.Post;

public class PostMapper {
    public static Post toEntity(CreatePostRequest req) {
        return Post.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .build();
    }

    public static void apply(UpdatePostRequest req, Post post) {
        if(req.getTitle() != null) post.setTitle(req.getTitle());
        if(req.getContent() != null) post.setContent(req.getContent());
        // updatedAt은 @PreUpdate로 자동 갱신
    }

    public static PostResponse toResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
