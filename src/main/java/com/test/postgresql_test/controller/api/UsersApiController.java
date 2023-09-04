package com.test.postgresql_test.controller.api;

import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.domain.dto.ResponseDto;
import com.test.postgresql_test.domain.dto.UsersJoinDto;
import com.test.postgresql_test.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> join(@RequestBody final UsersJoinDto usersJoinDto) {
        usersService.join(usersJoinDto);
        return new ResponseDto(HttpStatus.OK.value(), 1234);
    }
}
