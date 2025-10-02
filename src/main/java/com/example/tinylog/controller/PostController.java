package com.example.tinylog.controller;

import com.example.tinylog.dto.CreatePostRequest;
import com.example.tinylog.dto.UpdatePostRequest;
import com.example.tinylog.dto.PostResponse;
import com.example.tinylog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    //글 작성
    @PostMapping
    public PostResponse create(@Valid @RequestBody CreatePostRequest req){
        return service.create(req);
    }

    //글 목록
    @GetMapping
    public Page<PostResponse> list(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false) String query){
        return service.list(query, page, size);
    }

    //글 단건 조회
    @GetMapping("/{id}")
    public PostResponse get(@PathVariable Long id){
        return service.get(id);
    }

    //글 수정
    @PutMapping("/{id}")
    public PostResponse update(@PathVariable Long id, @RequestBody UpdatePostRequest req){
        return service.update(id, req);
    }

    //글 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
