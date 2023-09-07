package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Board;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.BoardDto;
import com.test.postgresql_test.domain.dto.BoardFormDto;
import com.test.postgresql_test.domain.dto.BoardFullDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingOrderByCreateDateAsc(String keyword, Pageable pageable);
    Page<Board> findByUsers_usernameContainingOrderByCreateDateAsc(String keyword, Pageable pageable);

    @Query("select new com.test.postgresql_test.domain.dto.BoardFormDto(b.title, b.content) from Board b where b.id = :id")
    BoardFormDto findByIdToDto(@Param("id") Long id);

    @Query("select new com.test.postgresql_test.domain.dto.BoardFullDto(b.id, b.title, b.content, b.viewCount, b.users) from Board b where b.id = :id")
    BoardFullDto findByIdToFullDto(@Param("id") Long id);

    @Query("select b from Board b where b.title like %:text% order by b.createDate desc")
    Page<Board> findContent(@Param("text") String text, Pageable pageable);

    @Query("select b from Board b left join fetch b.users u left join fetch b.replyList l where b.title like %:text% order by b.createDate desc")
    List<Board> findContentFetch(@Param("text") String text, Pageable pageable);

//    @Query("select new com.test.postgresql_test.domain.dto.BoardDto(b.id, b.title, b.content, b.viewCount,"
//            + " new com.test.postgresql_test.domain.dto.UserDto(u.id, u.username))"
//            + " from Board b"
//            + " join fetch b.users u"
//            + " where b.content like %:text%")
//    BoardDto findByWrite(@Param("text") String text);
}
