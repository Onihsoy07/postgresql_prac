package com.test.postgresql_test.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.postgresql_test.Service.ServiceImpl.BoardService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.dto.ResponseDto;
import com.test.postgresql_test.domain.dto.WriteBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseDto<String> writeBoard(@Validated @RequestBody final WriteBoardDto writeBoardDto,
                                          BindingResult bindingResult,
                                          @AuthenticationPrincipal PrincipalDetails principal) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            List<String> errors = new ArrayList<>();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                errors.add(allError.getDefaultMessage());
            }
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), errors);
        }

        boardService.boardSave(writeBoardDto, principal.getUsers());

        return new ResponseDto(HttpStatus.OK.value(), "완료");
    }

    @DeleteMapping("/board/{id}")
    public ResponseDto<String> deleteBoard(@PathVariable final Long id,
                                           @AuthenticationPrincipal PrincipalDetails principal) {
        Board findBoard = boardService.findById(id);
        if (principal == null || findBoard.getUsers().getId() != principal.getUsers().getId()) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "권한 없음");
        }
        boardService.boardDelete(id);

        return new ResponseDto(HttpStatus.OK.value(), "완료");
    }
}
