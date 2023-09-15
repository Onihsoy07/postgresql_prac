package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteBoardDto {

    private Long userId;

    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "글을 작성하세요.")
    private String content;

}
