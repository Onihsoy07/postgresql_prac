package com.test.postgresql_test.controller.api;

import com.test.postgresql_test.Service.ServiceImpl.ReplyService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.dto.ReplyFormDto;
import com.test.postgresql_test.domain.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("/board/{id}/reply")
    public ResponseDto<String> replySave(@PathVariable final Long id,
                                         @RequestBody final ReplyFormDto replyFormDto,
                                         @AuthenticationPrincipal PrincipalDetails principal) {
        replyService.replySave(id, principal.getUsers().getId(), replyFormDto);
        return new ResponseDto<>(HttpStatus.OK.value(), "완료");
    }
}
