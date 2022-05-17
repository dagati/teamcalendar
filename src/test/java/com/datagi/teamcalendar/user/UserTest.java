package com.datagi.teamcalendar.user;

import com.datagi.teamcalendar.domain.user.Authority;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class UserTest {

    private final UserRepository userRepository;

    @Autowired
    public UserTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("유저 저장 실패 테스트 (중복 이메일)")
    void saveUserFailureTest() {

        User user = User.builder()
                .email("alice@gmail.com")
                .password("alice")
                .name("alice")
                .authority(Authority.USER)
                .build();

        assertThatThrownBy(() -> userRepository.save(user)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("유저 저장 성공 테스트")
    void saveUserSuccessTest() {
        User user = User.builder()
                .name("pllap")
                .email("pllap@gmail.com")
                .password("p@ssword")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }
}
