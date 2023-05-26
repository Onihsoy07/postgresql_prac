package com.test.postgresql_test.domain.dto;

import com.test.postgresql_test.domain.Entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private String title;
    private String content;

    @Builder
    public BoardDto(String title, String content, int replyCnt) {
        this.title = title;
        this.content = content;
    }

    static BoardDto convertToDto(Board board) {
        return new BoardDto().builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
