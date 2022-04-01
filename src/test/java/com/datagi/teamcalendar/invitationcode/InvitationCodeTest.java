package com.datagi.teamcalendar.invitationcode;

import com.datagi.teamcalendar.domain.invitationcode.InvitationCode;
import com.datagi.teamcalendar.domain.invitationcode.repository.InvitationCodeRepository;
import com.datagi.teamcalendar.domain.team.Team;
import com.datagi.teamcalendar.domain.team.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

@Transactional
@SpringBootTest
class InvitationCodeTest {

    private final TeamRepository teamRepository;
    private final InvitationCodeRepository invitationCodeRepository;

    @Autowired
    public InvitationCodeTest(
            TeamRepository teamRepository,
            InvitationCodeRepository invitationCodeRepository
    ) {
        this.teamRepository = teamRepository;
        this.invitationCodeRepository = invitationCodeRepository;
    }

    @Test
    @DisplayName("초대 코드 생성 성공 테스트")
    void saveInvitationCodeSuccessTest() {

        Team team = teamRepository.getById(1L);

        InvitationCode invitationCode = InvitationCode.builder()
                .token("g897vbgs125jnbp0")
                .team(team)
                .build();
        invitationCodeRepository.save(invitationCode);

        Assertions.assertThat(invitationCode.getId()).isNotNull();
    }

    @Test
    @DisplayName("초대 코드 생성 실패 테스트 (토큰 누락)")
    void saveInvitationCodeFailureTest1() {

        Team team = teamRepository.getById(1L);

        InvitationCode invitationCode = InvitationCode.builder()
                .team(team)
                .build();

        Assertions.assertThatThrownBy(() -> invitationCodeRepository.save(invitationCode))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("초대 코드 생성 실패 테스트 (너무 긴 토큰)")
    void saveInvitationCodeFailureTest2() {

        Team team = teamRepository.getById(1L);

        InvitationCode invitationCode = InvitationCode.builder()
                .token("89yhtg4eqa298t4289031rq9031rhj890")
                .team(team)
                .build();

        Assertions.assertThatThrownBy(() -> invitationCodeRepository.save(invitationCode))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("팀 정보 누락")
    void saveInvitationCodeFailureTest3() {
        InvitationCode invitationCode = InvitationCode.builder()
                .token("g897vbgs125jnbp0")
                .build();

        Assertions.assertThatThrownBy(() -> invitationCodeRepository.save(invitationCode))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
