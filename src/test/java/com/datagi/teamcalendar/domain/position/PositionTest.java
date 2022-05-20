package com.datagi.teamcalendar.domain.position;

import com.datagi.teamcalendar.domain.member.Member;
import com.datagi.teamcalendar.domain.member.repository.MemberRepository;
import com.datagi.teamcalendar.domain.position.repository.PositionRepository;
import com.datagi.teamcalendar.domain.role.Role;
import com.datagi.teamcalendar.domain.role.repository.RoleRepository;
import com.datagi.teamcalendar.domain.team.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PositionTest {

    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public PositionTest(
            RoleRepository roleRepository,
            MemberRepository memberRepository,
            PositionRepository positionRepository
    ) {
        this.roleRepository = roleRepository;
        this.memberRepository = memberRepository;
        this.positionRepository = positionRepository;
    }

    @Test
    @DisplayName("소속 생성 실패 테스트 (역할 정보 누락)")
    void createPositionFailureTest1() {

        Member member = memberRepository.getById(3L);

        Position position = Position.builder()
                .member(member)
                .build();

        Assertions.assertThatThrownBy(() -> positionRepository.save(position))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("소속 생성 실패 테스트 (팀원 정보 누락)")
    void createPositionFailureTest2() {

        Role role = roleRepository.getById(1L);

        Position position = Position.builder()
                .role(role)
                .build();

        Assertions.assertThatThrownBy(() -> positionRepository.save(position))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("소속 생성 실패 테스트 (팀원이 이미 역할에 소속되어 있음)")
    void createPositionFailureTest3() {

        Role role = roleRepository.getById(1L);
        Member member = memberRepository.getById(1L);

        Position position = Position.builder()
                .role(role)
                .member(member)
                .build();

        Assertions.assertThatThrownBy(() -> positionRepository.save(position))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("역할 생성 성공 테스트")
    void createRoleSuccessTest() {

        Role role = roleRepository.getById(1L);
        Member member = memberRepository.getById(2L);

        Position position = Position.builder()
                .role(role)
                .member(member)
                .build();
        positionRepository.save(position);

        Assertions.assertThat(position.getId()).isNotNull();
    }
}
