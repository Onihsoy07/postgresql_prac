package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.WriteBoardDto;
import com.test.postgresql_test.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void boardSave(WriteBoardDto writeBoardDto, Users users) {
        Board board = new Board().builder()
                .title(writeBoardDto.getTitle())
                .content(writeBoardDto.getContent())
                .users(users)
                .build();
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("보드 못찾음");
        });
    }

}
