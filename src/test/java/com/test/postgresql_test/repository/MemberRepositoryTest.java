package com.test.postgresql_test.repository;

import com.test.postgresql_test.domain.Entity.Member;
import com.test.postgresql_test.domain.Entity.Team;
import com.test.postgresql_test.domain.dto.MemberDto;
import com.test.postgresql_test.domain.repository.MemberRepository;
import com.test.postgresql_test.domain.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
//@TestPropertySource(locations="classpath:application-test.properties")
public class MemberRepositoryTest {

//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private TeamRepository teamRepository;
//
//
//    @Test
//    @DisplayName("repository Dto로 조회 출력")
//    public void findMemberDto() {
//        //given
//        Team team1 = new Team("team1");
//        Team team2 = new Team("team2");
//        Team team3 = new Team("team3");
//        teamRepository.save(team1);
//        teamRepository.save(team2);
//        teamRepository.save(team3);
//
//        Member member1 = new Member("park", team1);
//        Member member2 = new Member("lee", team2);
//        Member member3 = new Member("kim", team3);
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//
//        //when
//        List<MemberDto> memberDto = memberRepository.findMemberDto();
//        for (MemberDto dto : memberDto) {
//            System.out.println("memberDto = " + dto);
//        }
//
//        //then
//        assertThat(memberDto.size()).isEqualTo(3);
//    }

}
