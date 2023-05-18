package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
