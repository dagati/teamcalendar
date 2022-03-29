package com.datagi.teamcalendar.user;

import com.datagi.teamcalendar.domain.user.Authority;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 저장 성공 테스트")
    void saveUserSuccessTest() {
        User user = User.builder()
                .email("user@gmail.com")
                .password("iamabuser")
                .name("user")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }

    @Test
    @DisplayName("유저 저장 실패 테스트 (중복 이메일)")
    void saveUserFailureTest() {

        System.out.println(userRepository.findAll());

        User user = User.builder()
                .email("alice@gmail.com")
                .password("alice")
                .name("alice")
                .authority(Authority.USER)
                .build();

        assertThatThrownBy(() -> userRepository.save(user)).isInstanceOf(DataIntegrityViolationException.class);
    }
}
