package com.test.postgresql_test.controller.api;

import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.domain.Entity.Reply;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.UsersJoinDto;
import com.test.postgresql_test.domain.repository.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsersApiControllerTest {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Test
//    void join() {
//        UsersJoinDto usersJoinDto = new UsersJoinDto().builder()
//                        .username("wwww")
//                        .password("wwww")
//                        .build();
//        usersService.join(usersJoinDto);
//        Optional<Users> result = usersRepository.findByUsername("wwww");
//        Users users = null;
//        if (result.isEmpty()) {
//            fail();
//        } else {
//            users = result.get();
//        }
//
//        assertThat(users.getUsername()).isEqualTo(usersJoinDto.getUsername());
////        뭐지 왜 다르지?
////        assertThat(users.getPassword()).isEqualTo(passwordEncoder.encode(usersJoinDto.getPassword()));
//    }

    
}