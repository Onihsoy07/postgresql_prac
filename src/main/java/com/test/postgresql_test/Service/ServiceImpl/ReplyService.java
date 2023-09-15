package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Reply;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.ReplyFormDto;
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
    public void replySave(Long boardId, Long usersId, ReplyFormDto replyFormDto) {
        if (replyFormDto.getReplyId() == 0) {
            replyFormDto.setReplyId(null);
        }
        replyRepository.replySave(boardId, usersId, replyFormDto.getReplyId(), replyFormDto.getComment(), replyFormDto.getDepth());
    }
}
