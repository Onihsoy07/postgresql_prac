package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingOrderByCreateDateAsc(String keyword, Pageable pageable);

}
