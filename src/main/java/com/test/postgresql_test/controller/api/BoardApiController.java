package com.test.postgresql_test.controller.api;

import com.test.postgresql_test.Service.ServiceImpl.BoardService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.dto.ResponseDto;
import com.test.postgresql_test.domain.dto.WriteBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseDto<String> writeBoard(@RequestBody final WriteBoardDto writeBoardDto,
                                             @AuthenticationPrincipal PrincipalDetails principal) {
        boardService.boardSave(writeBoardDto, principal.getUsers());
        return new ResponseDto(HttpStatus.OK.value(), "완료");
    }
}
