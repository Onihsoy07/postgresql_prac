package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.CfrData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CfrDataRepository extends JpaRepository<CfrData, Long> {

    List<CfrData> findByUsersId_IdOrderByCreateDateDesc(Long id);

    CfrData findTopByUsersId_IdOrderByCreateDateDesc(Long id);

}
