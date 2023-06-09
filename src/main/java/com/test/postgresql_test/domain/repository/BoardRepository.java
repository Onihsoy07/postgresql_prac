package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.dto.BoardDto;
import com.test.postgresql_test.domain.dto.BoardFullDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingOrderByCreateDateAsc(String keyword, Pageable pageable);
    Page<Board> findByUsers_usernameContainingOrderByCreateDateAsc(String keyword, Pageable pageable);

    @Query("select new com.test.postgresql_test.domain.dto.BoardDto(b.title, b.content) from Board b where b.id = :id")
    BoardDto findByIdToDto(@Param("id") Long id);

    @Query("select new com.test.postgresql_test.domain.dto.BoardFullDto(b.id, b.title, b.content, b.viewCount, b.users) from Board b where b.id = :id")
    BoardFullDto findByIdToFullDto(@Param("id") Long id);

}
