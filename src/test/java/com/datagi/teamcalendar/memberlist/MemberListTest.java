package com.datagi.teamcalendar.memberlist;

import com.datagi.teamcalendar.domain.memberlist.MemberList;
import com.datagi.teamcalendar.domain.memberlist.repository.MemberListRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
class MemberListTest {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final MemberListRepository memberListRepository;

    @Autowired
    public MemberListTest(TeamRepository teamRepository, UserRepository userRepository, MemberListRepository memberListRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.memberListRepository = memberListRepository;
    }

    @Test
    @DisplayName("멤버 리스트 추가 성공 테스트")
    void addToMemberListSuccessTest() {
        Team team = teamRepository.getById(1L);
        User user = userRepository.getById(2L);

        MemberList memberList = MemberList.builder()
                .team(team)
                .user(user)
                .build();
        memberListRepository.save(memberList);

        Assertions.assertThat(memberList.getId()).isNotNull();
    }

    @Test
    @DisplayName("멤버 리스트 추가 실패 테스트 (팀에 이미 유저가 있음)")
    void addToMemberListFailureTest1() {
        Team team = teamRepository.getById(1L);
        User user = userRepository.getById(1L);

        MemberList memberList = MemberList.builder()
                .team(team)
                .user(user)
                .build();

        Assertions.assertThatThrownBy(() -> memberListRepository.save(memberList))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("멤버 리스트 추가 실패 테스트 (팀 정보 누락)")
    void addToMemberListFailureTest2() {
        User user = userRepository.getById(1L);

        MemberList memberList = MemberList.builder()
                .user(user)
                .build();

        Assertions.assertThatThrownBy(() -> memberListRepository.save(memberList))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("멤버 리스트 추가 실패 테스트 (유저 정보 누락)")
    void addToMemberListFailureTest3() {
        Team team = teamRepository.getById(1L);

        MemberList memberList = MemberList.builder()
                .team(team)
                .build();

        Assertions.assertThatThrownBy(() -> memberListRepository.save(memberList))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
