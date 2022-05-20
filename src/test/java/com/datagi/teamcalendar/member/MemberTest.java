package com.datagi.teamcalendar.member;

import com.datagi.teamcalendar.domain.member.Member;
import com.datagi.teamcalendar.domain.member.repository.MemberRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class MemberTest {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberTest(
            TeamRepository teamRepository,
            UserRepository userRepository,
            MemberRepository memberRepository
    ) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Test
    @DisplayName("팀원 추가 실패 테스트 (팀에 이미 유저가 있음)")
    void addToMemberListFailureTest1() {
        Team team = teamRepository.getById(1L);
        User user = userRepository.getById(1L);

        Member member = Member.builder()
                .team(team)
                .user(user)
                .build();

        Assertions.assertThatThrownBy(() -> memberRepository.save(member))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("팀원 추가 실패 테스트 (팀 정보 누락)")
    void addToMemberListFailureTest2() {
        User user = userRepository.getById(1L);

        Member member = Member.builder()
                .user(user)
                .build();

        Assertions.assertThatThrownBy(() -> memberRepository.save(member))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("팀원 추가 실패 테스트 (유저 정보 누락)")
    void addToMemberListFailureTest3() {
        Team team = teamRepository.getById(1L);

        Member member = Member.builder()
                .team(team)
                .build();

        Assertions.assertThatThrownBy(() -> memberRepository.save(member))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("팀원 추가 성공 테스트")
    void addToMemberListSuccessTest() {
        Team team = teamRepository.getById(1L);
        User user = userRepository.getById(2L);

        Member member = Member.builder()
                .team(team)
                .user(user)
                .build();
        memberRepository.save(member);

        Assertions.assertThat(member.getId()).isNotNull();
    }
}
