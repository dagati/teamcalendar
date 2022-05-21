package com.datagi.teamcalendar.domain.team;

import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TeamTest {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamTest(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Test
    @DisplayName("팀 저장 실패 테스트 (팀 이름 누락)")
    void saveTeamFailureTest1() {

        Team team = Team.builder()
                .build();

        assertThatThrownBy(() -> teamRepository.save(team))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("팀 저장 실패 테스트 (너무 긴 팀 이름)")
    void saveTeamFailureTest2() {

        Team team = Team.builder()
                .name("a very long team name")
                .build();

        assertThatThrownBy(() -> teamRepository.save(team))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("팀 저장 실패 테스트 (길이가 다른 입장 코드)")
    void saveTeamFailureTest3() {

        Team team = Team.builder()
                .name("dagati")
                .code("123456789012345678901234567890")
                .build();

        assertThatThrownBy(() -> teamRepository.save(team))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("팀 저장 성공 테스트")
    void saveTeamSuccessTest() {

        Team team = Team.builder()
                .name("dagati")
                .build();
        teamRepository.save(team);

        assertThat(team.getId()).isNotNull();
    }
}
