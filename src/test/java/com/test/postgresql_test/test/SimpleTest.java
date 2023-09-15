package com.test.postgresql_test.test;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Reply;
import com.test.postgresql_test.domain.dto.BoardDto;
import com.test.postgresql_test.domain.repository.BoardRepository;
import com.test.postgresql_test.domain.repository.ReplyRepository;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;

@Slf4j
@SpringBootTest
public class SimpleTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;


    @Test
    void findByWriteTest() {
        Page<Board> board = boardRepository.findContent("안", PageRequest.of(0, 3));

        List<Board> boardFetch = boardRepository.findContentFetch("안", PageRequest.of(0, 3));
        PageImpl<BoardDto> boards = new PageImpl<>(BoardDto.convertToBoardDtoList(boardFetch), PageRequest.of(0, 3), board.getTotalElements());

        System.out.println("board = " + board);
    }

    @Test
    void findByBoard_id_Reply() {
        List<Reply> byBoardId = replyRepository.findByBoard_Id(15L);
        System.out.println("byBoardId = " + byBoardId);
    }



}
