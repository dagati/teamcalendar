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

        User result = userRepository.findByEmail(user.getEmail())
                .orElse(null);
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    void equalsSuccessTest() {
        User saved = User.builder()
                .email("alice@google.com")
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();
        userRepository.save(saved);

        User read = userRepository.findByEmail(saved.getEmail())
                .orElse(null);

        assertEquals(saved, read);
    }

    @Test
    void equalsFailureTest() {
        userRepository.save(
                User.builder()
                        .email("alice@google.com")
                        .password("1q2w3e4r!!")
                        .name("alice")
                        .authority(Authority.USER)
                        .build()
        );
        userRepository.save(
                User.builder()
                        .email("bob@google.com")
                        .password("p@ssword")
                        .name("bob")
                        .authority(Authority.USER)
                        .build()
        );

        User alice = userRepository.findByEmail("alice@google.com")
                .orElse(null);
        User bob = userRepository.findByEmail("bob@google.com")
                .orElse(null);

        assertNotEquals(alice, bob);
    }

    @Test
    void toStringTest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        User alice = User.builder()
                .email("alice@google.com")
                .password("1q2w3e4r!!")
                .name("alice")
                .authority(Authority.USER)
                .build();

        logger.info(alice.toString());
    }

}
