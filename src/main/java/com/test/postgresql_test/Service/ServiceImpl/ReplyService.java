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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    @Transactional(readOnly = true)
    public List<Reply> sortReply(Long boardId) {
        List<Reply> byBoardId = replyRepository.findByBoard_IdOrderByReply_IdDescIdAsc(boardId);

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

        return replyList;
    }


}
