package com.test.postgresql_test.controller;

import com.test.postgresql_test.Service.ServiceImpl.BoardService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.dto.ResponseDto;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public String writeBoard(@Validated @ModelAttribute final WriteBoardDto writeBoardDto,
                             BindingResult bindingResult,
                             @AuthenticationPrincipal PrincipalDetails principal) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "board/writeBoard";
        }

        boardService.boardSave(writeBoardDto, principal.getUsers());
        return "redirect:/";
    }
}
