package com.example.tinylog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CreatePostRequest {
    @NotBlank @Size(max = 200)
    private String title;

    @NotBlank
    private String content;
}
