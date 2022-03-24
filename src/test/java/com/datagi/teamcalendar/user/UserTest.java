package com.datagi.teamcalendar.user;

import com.datagi.teamcalendar.domain.user.Authority;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserTest() {
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

    /**
     * equals 성공 테스트 (동일한 레퍼런스)
     */
    @Test
    void equalsSuccessTest1() {
        User alice = User.builder()
                .email("alice@google.com")
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();
        User bob = alice;

        assertEquals(alice, bob);
    }

    /**
     * equals 성공 테스트 (동일한 데이터)
     */
    @Test
    void equalsSuccessTest2() {
        String aliceEmail = "alice@google.com";
        User alice = User.builder()
                .email(aliceEmail)
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();
        userRepository.save(alice);

        User aliceOnDB = userRepository.findByEmail(aliceEmail).orElse(null);

        assertEquals(alice, aliceOnDB);
    }

    /**
     * toString 테스트
     */
    @Test
    void toStringTest() {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        User user = User.builder()
                .email("alice@google.com")
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();

        logger.info(user.toString());
    }
}
