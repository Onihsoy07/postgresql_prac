package com.test.postgresql_test.controller;

import com.test.postgresql_test.Service.ServiceImpl.UsersServiceImpl;
import com.test.postgresql_test.domain.dto.JwtTokenDto;
import com.test.postgresql_test.domain.dto.ResponseDto;
import com.test.postgresql_test.domain.dto.UsersLonginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsersServiceImpl usersService;

    @PostMapping("/auth/login")
    public ResponseDto<JwtTokenDto> login(@RequestBody UsersLonginDto usersLonginDto) {//@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("id : {}, pw : {}", usersLonginDto.getId(), usersLonginDto.getPassword());
        JwtTokenDto tokenInfo = usersService.login(usersLonginDto.getId(), usersLonginDto.getPassword());
        return new ResponseDto<>(HttpStatus.OK.value(), tokenInfo);
    }
}
