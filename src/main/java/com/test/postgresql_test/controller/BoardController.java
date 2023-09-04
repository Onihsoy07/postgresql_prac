package com.test.postgresql_test.controller;

import com.test.postgresql_test.Service.ServiceImpl.BoardService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.dto.ResponseDto;
import com.test.postgresql_test.domain.dto.UpdateBoardDto;
import com.test.postgresql_test.domain.dto.WriteBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/form")
    public String writeBoard(@ModelAttribute final WriteBoardDto writeBoardDto,
                             @AuthenticationPrincipal PrincipalDetails principal) {
        return "board/writeForm";
    }

    @GetMapping("/board/{boardId}/updateForm")
    public String updateBoardForm(@PathVariable Long boardId,
                                  @AuthenticationPrincipal PrincipalDetails principal,
                                  Model model) {
        Board board = boardService.findById(boardId);

        if (principal == null || principal.getUsers().getId() != board.getUsers().getId()) {
            return "redirect:/";
        }

        UpdateBoardDto updateBoardDto = new UpdateBoardDto().builder()
                                        .id(board.getId())
                                        .title(board.getTitle())
                                        .content(board.getContent())
                                        .build();

        model.addAttribute("updateBoardDto", updateBoardDto);

        return "board/updateBoard";
    }

    @PostMapping("/board/{boardId}/updateForm")
    public String updateBoard(@Valid @ModelAttribute UpdateBoardDto updateBoardDto,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal PrincipalDetails principal) {
        if (principal == null || principal.getUsers().getId() != boardService.findById(updateBoardDto.getId()).getUsers().getId()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            return "board/updateBoard";
        }

        boardService.updateBoard(updateBoardDto);

        return "redirect:/";
    }

}
