package com.test.postgresql_test.domain.dto;

import com.test.postgresql_test.domain.Entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFormDto {

    @NotBlank(message = "제목을 입력하세요")
    private String title;
    @NotBlank(message = "글을 작성하세요.")
    private String content;

    @Builder
    public BoardFormDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static BoardFormDto convertToDto(Board board) {
        return new BoardFormDto().builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
