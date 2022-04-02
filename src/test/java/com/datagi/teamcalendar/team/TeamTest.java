package com.datagi.teamcalendar.team;

import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class TeamTest {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamTest(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Test
    @DisplayName("팀 저장 성공 테스트")
    void saveTeamSuccessTest() {

        User leader = userRepository.getById(1L);

        Team team = Team.builder()
                .name("dagati")
                .leader(leader)
                .build();
        teamRepository.save(team);

        assertThat(team.getId()).isNotNull();
    }

    @Test
    @DisplayName("팀 저장 실패 테스트 (팀 이름이 없음)")
    void saveTeamFailureTest() {

        User leader = userRepository.getById(1L);

        Team team = Team.builder()
                .leader(leader)
                .build();

        assertThatThrownBy(() -> teamRepository.save(team))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
