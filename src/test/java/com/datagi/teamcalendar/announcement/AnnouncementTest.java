package com.datagi.teamcalendar.announcement;

import com.datagi.teamcalendar.domain.announcement.Announcement;
import com.datagi.teamcalendar.domain.announcement.repository.AnnouncementRepository;
import com.datagi.teamcalendar.domain.user.Authority;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class AnnouncementTest {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveAnnouncementTest() {

        User user = User.builder()
                .email("kim11@naver.com")
                .name("ggo")
                .password("1234")
                .authority(Authority.ADMIN)
                .build();
        userRepository.save(user);

        Announcement announcement = Announcement.builder()
                .title("hello friends")
                .content("test content")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();
        announcementRepository.save(announcement);

        Announcement result = announcementRepository.findById(announcement.getId()).orElse(null);
        assertNotNull(result);
        assertEquals(announcement.getId(), result.getId());
    }

}
