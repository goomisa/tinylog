package com.example.tinylog.service;

import com.example.tinylog.domain.Post;
import com.example.tinylog.dto.CreatePostRequest;
import com.example.tinylog.dto.UpdatePostRequest;
import com.example.tinylog.dto.PostResponse;
import com.example.tinylog.dto.PostMapper;
import com.example.tinylog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repo;

    //글 생성
    public PostResponse create(CreatePostRequest req){
        Post post = PostMapper.toEntity(req);
        Post saved = repo.save(post);
        return PostMapper.toResponse(saved);
    }

    //글 목록 (페이징)
    public Page<PostResponse> list(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return repo.findAll(pageable).map(PostMapper::toResponse);
    }

    //글 단건 조회
    public PostResponse get(Long id){
        var post = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "글을 찾을 수 없습니다:" + id));
        return PostMapper.toResponse(post);
    }

    //글 수정
    public PostResponse update(Long id, UpdatePostRequest req){
        var post = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "글을 찾을 수 없습니다:" + id));

        PostMapper.apply(req, post); //DTO -> 엔티티 적용
        var updated = repo.save(post);
        return PostMapper.toResponse(updated);
    }

    //글 삭제
    public void delete(Long id){
        repo.deleteById(id);
    }
}
