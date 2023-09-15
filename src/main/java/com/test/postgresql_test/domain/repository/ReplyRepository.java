package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByBoard_IdOrderByCreateDateAsc(Long id);

    @Modifying
    @Query(value = "INSERT INTO REPLY(comment, level, board_id, users_id, reply_id) VALUES (:comment, :level, :boardId, :memberId, :replyId)", nativeQuery = true)
    void replySave(@Param("boardId") Long boardId, @Param("memberId") Long memberId, @Param("replyId") Long replyId,
                   @Param("comment") String comment, @Param("level") int level);

    List<Reply> findByBoard_Id(Long boardId);
}
