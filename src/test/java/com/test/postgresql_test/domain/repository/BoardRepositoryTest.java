package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Role;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.BoardFormDto;
import com.test.postgresql_test.domain.dto.BoardFullDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

//    @Autowired
//    private BoardRepository boardRepository;
//    @Autowired
//    private UsersRepository usersRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Test
//    @Transactional
//    void findByIdToDto() {
//        //given
//        Board board = new Board().builder()
//                .title("hello")
//                .content("hello world")
//                .build();
//        Board savedBoard = boardRepository.save(board);
//
//        //when
//        BoardFormDto boardFormDto = boardRepository.findByIdToDto(savedBoard.getId());
//        System.out.println("boardDto = " + boardFormDto);
//
//        //then
//        assertThat(boardFormDto.getTitle()).isEqualTo(board.getTitle());
//        assertThat(boardFormDto.getContent()).isEqualTo(board.getContent());
//
//    }
//
//    //Users 받는건 가능 UsersDto로 받아야 할까?
//    @Test
//    @Transactional
//    void findByIdToFullDto() {
//        //given
//        Users users = new Users().builder()
//                .username("park")
//                .password(bCryptPasswordEncoder.encode("park"))
//                .email("park@email.email")
//                .role(Role.ADMIN)
//                .build();
//        Users savedUser = usersRepository.save(users);
//        Board board = new Board().builder()
//                .title("hello")
//                .content("hello world")
//                .viewCount(0)
//                .users(savedUser)
//                .build();
//        Board savedBoard = boardRepository.save(board);
//
//        //when
//        BoardFullDto boardFullDto = boardRepository.findByIdToFullDto(savedBoard.getId());
//        System.out.println("boardFullDto = " + boardFullDto);
//        System.out.println(boardFullDto.getUsers().getRole());
//        //then
//    }
}