package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
