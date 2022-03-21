package com.datagi.teamcalendar.user;

import com.datagi.teamcalendar.domain.user.Authority;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
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

}
