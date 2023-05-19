package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Reply;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.ReplyDto;
import com.test.postgresql_test.domain.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardService boardService;
    private final UsersService usersService;

    @Transactional
    public void replySave(Long boardId, Long usersId, ReplyDto replyDto) {
        Users users = usersService.findById(usersId);
        Board board = boardService.findById(boardId);
        Reply reply = new Reply().builder()
                .level(0)
                .comment(replyDto.getComment())
                .users(users)
                .board(board)
                .build();
        replyRepository.save(reply);
    }
}
