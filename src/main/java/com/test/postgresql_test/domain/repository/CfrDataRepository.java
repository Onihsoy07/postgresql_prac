package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.CfrData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CfrDataRepository extends JpaRepository<CfrData, Long> {
}
