package com.test.postgresql_test.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Reply;
import com.test.postgresql_test.domain.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String createDate;
    private int viewCount;
    private int replyCount;
    private UserDto userDto;
    private List<ReplyDto> replyDtoList;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createDate = board.getCreateDate();
        this.viewCount = board.getViewCount();
        this.replyCount = board.getReplyCount();
        this.userDto = new UserDto(board.getUsers());
        this.replyDtoList = ReplyDto.convertToReplyDtoList(board.getReplyList());
    }

    public static List<BoardDto> convertToBoardDtoList(List<Board> boardList) {
        return boardList.stream().map(BoardDto::new).collect(Collectors.toList());
    }
}
