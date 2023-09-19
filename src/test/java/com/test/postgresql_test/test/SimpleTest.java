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

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
        List<Reply> byBoardId = replyRepository.findByBoard_IdOrderByReply_IdDescIdAsc(17L);

        Stack<Reply> stack = new Stack<>();
        List<Reply> replyList = new ArrayList<>();

        for (int i = byBoardId.size() - 1; i >= 0; i--) {
            if (byBoardId.get(i).getReply() != null) {
                break;
            }
            stack.push(byBoardId.get(i));
            byBoardId.remove(i);
        }

        while (stack.size() > 0) {
            Reply pop = stack.pop();
            replyList.add(pop);

            for (int i = byBoardId.size() - 1; i >= 0; i--) {
                if (pop.getId() < byBoardId.get(i).getReply().getId()) {
                    break;
                }
                if (pop.getId() == byBoardId.get(i).getReply().getId()) {
                    stack.push(byBoardId.get(i));
                    byBoardId.remove(i);
                }
            }
        }
    }



}
