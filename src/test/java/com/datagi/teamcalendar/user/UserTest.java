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

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 저장 성공 테스트")
    void saveUserSuccessTest() {
        User user = User.builder()
                .email("alice@google.com")
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        User result = userRepository.findByEmail(user.getEmail()).orElse(null);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    @DisplayName("유저 저장 실패 테스트 (중복 이메일)")
    void saveUserFailureTest() {

        String email = "duplicated@google.com";

        User user = User.builder()
                .email(email)
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        assertThrows(DataIntegrityViolationException.class, () -> {
            User anotherUser = User.builder()
                    .email(email)
                    .password("1q2w3e4r!!")
                    .name("alice")
                    .authority(Authority.USER)
                    .build();
            userRepository.save(anotherUser);
        });
    }
}
