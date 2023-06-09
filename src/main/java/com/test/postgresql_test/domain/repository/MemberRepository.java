package com.test.postgresql_test.domain.repository;

import com.test.postgresql_test.domain.Entity.Member;
import com.test.postgresql_test.domain.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    @Query("select new com.test.postgresql_test.domain.dto.MemberDto(m.id, m.name, t.name) from Member m join m.team t")
//    List<MemberDto> findMemberDto();
}
