package com.example.tinylog.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdatePostRequest {
    @Size(max = 200)
    private String title; // 부분수정 가능하게 null 허용
    private String content; //null이면 그대로 유지
}
