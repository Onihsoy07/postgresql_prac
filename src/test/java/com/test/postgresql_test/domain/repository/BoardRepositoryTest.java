package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.dto.BoardDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    void findByIdToDto() {
        //given
        Board board = new Board().builder()
                .title("hello")
                .content("hello world")
                .build();
        Board savedBoard = boardRepository.save(board);

        //when
        BoardDto boardDto = boardRepository.findByIdToDto(savedBoard.getId());
        System.out.println("boardDto = " + boardDto);

        //then
        assertThat(boardDto.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardDto.getContent()).isEqualTo(board.getContent());

    }
}