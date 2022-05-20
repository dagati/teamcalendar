package com.datagi.teamcalendar.domain.teamrelation;

import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import com.datagi.teamcalendar.domain.teamrelation.repository.TeamRelationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TeamRelationTest {

    private final TeamRepository teamRepository;
    private final TeamRelationRepository teamRelationRepository;

    @Autowired
    public TeamRelationTest(
            TeamRepository teamRepository,
            TeamRelationRepository teamRelationRepository
    ) {
        this.teamRepository = teamRepository;
        this.teamRelationRepository = teamRelationRepository;
    }

    @Test
    @DisplayName("팀 관계 저장 실패 테스트 (부모 팀 누락)")
    @Order(1)
    void saveTeamRelationFailureTest1() {

        Team childTeam = teamRepository.getById(1L);

        TeamRelation teamRelation = TeamRelation.builder()
                .child(childTeam)
                .depth(0)
                .build();

        Assertions.assertThatThrownBy(() -> teamRelationRepository.save(teamRelation))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("팀 관계 저장 실패 테스트 (자식 팀 누락)")
    @Order(2)
    void saveTeamRelationFailureTest2() {

        Team parentTeam = teamRepository.getById(1L);

        TeamRelation teamRelation = TeamRelation.builder()
                .parent(parentTeam)
                .depth(0)
                .build();

        Assertions.assertThatThrownBy(() -> teamRelationRepository.save(teamRelation))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("팀 관계 저장 실패 테스트 (높이 누락)")
    @Order(3)
    void saveTeamRelationFailureTest3() {

        Team parentTeam = teamRepository.getById(1L);
        Team childTeam = teamRepository.getById(1L);

        TeamRelation teamRelation = TeamRelation.builder()
                .parent(parentTeam)
                .child(childTeam)
                .build();

        Assertions.assertThatThrownBy(() -> teamRelationRepository.save(teamRelation))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("팀 관계 저장 실패 테스트 (이미 저장된 관계)")
    @Order(4)
    void saveTeamRelationFailureTest4() {

        Team parentTeam = teamRepository.getById(1L);
        Team childTeam = teamRepository.getById(1L);
        int depth = 0;

        TeamRelation teamRelation = TeamRelation.builder()
                .parent(parentTeam)
                .child(childTeam)
                .depth(depth)
                .build();
        teamRelationRepository.save(teamRelation);

        Assertions.assertThatThrownBy(() -> teamRelationRepository.save(teamRelation))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("팀 관계 저장 성공 테스트")
    @Order(5)
    void saveTeamRelationSuccessTest() {

        Team parentTeam = teamRepository.getById(1L);
        Team childTeam = teamRepository.getById(2L);
        int depth = 1;

        TeamRelation teamRelation = TeamRelation.builder()
                .parent(parentTeam)
                .child(childTeam)
                .depth(depth)
                .build();
        teamRelationRepository.save(teamRelation);

        Assertions.assertThat(teamRelation.getId()).isNotNull();
    }
}
