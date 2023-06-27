package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.WriteBoardDto;
import com.test.postgresql_test.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<Board> searchBoard(String target, String keyword, Pageable pageable) {
        System.out.println(target);
        if ("title".equals(target)) {
            return boardRepository.findByTitleContainingOrderByCreateDateAsc(keyword, pageable);
        } else if ("writer".equals(target)) {
            return boardRepository.findByUsers_usernameContainingOrderByCreateDateAsc(keyword, pageable);
        } else {
            throw new IllegalArgumentException("타겟 에러");
        }
    }

    @Transactional
    private void addViewCount(Long boardId) {
        Board board = findById(boardId);
        board.setViewCount(board.getViewCount()+1);
        boardRepository.save(board);
    }

    @Transactional
    public void viewCount(Long boardId, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie thisCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("viewBoardList".equals(cookie.getName())) {
                    thisCookie = cookie;
                    break;
                }
            }
        }

        if (thisCookie == null) {
            thisCookie = new Cookie("viewBoardList", String.format("_%d_", boardId));
            thisCookie.setMaxAge(calcEndSec());
            thisCookie.setPath("/");
            response.addCookie(thisCookie);
            addViewCount(boardId);
//            return response;
        } else if (thisCookie.getValue().contains(String.format("_%d_", boardId))) {
//            return response;
        } else {
            thisCookie.setValue(thisCookie.getValue() + String.format("%d_", boardId));
            response.addCookie(thisCookie);
            addViewCount(boardId);
//            return response;
        }
    }

    private int calcEndSec() {
        // 현재 하루의 종료 시간, 2022-08-20T23:59:59.9999999
        LocalDateTime todayEndTime = LocalDate.now().atTime(LocalTime.MAX);

        // 현재 시간, 2022-08-20T19:39:10.936
        LocalDateTime currentTime = LocalDateTime.now();

        // 하루 종료 시간을 시간초로 변환
        long todayEndSecond = todayEndTime.toEpochSecond(ZoneOffset.UTC);

        // 현재 시간을 시간초로 변환
        long currentSecond = currentTime.toEpochSecond(ZoneOffset.UTC);

        // 하루 종료까지 남은 시간초
        long remainingTime = todayEndSecond - currentSecond;

        return Long.valueOf(remainingTime).intValue();
    }

}
