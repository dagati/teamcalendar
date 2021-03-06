package com.datagi.teamcalendar.announcement;

import com.datagi.teamcalendar.domain.announcement.Announcement;
import com.datagi.teamcalendar.domain.announcement.repository.AnnouncementRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class AnnouncementTest {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnnouncementTest(
            AnnouncementRepository announcementRepository,
            UserRepository userRepository
    ) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("공지사항 저장 성공 테스트")
    void saveAnnouncementTest() {

        User user = userRepository.getById(1L);

        Announcement announcement = Announcement.builder()
                .title("hello friends")
                .content("test content")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();
        announcementRepository.save(announcement);

        assertThat(announcement.getId()).isNotNull();
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (제목이 없음)")
    void saveAnnouncementFailureTest1() {

        User user = userRepository.getById(1L);

        Announcement announcement = Announcement.builder()
                .content("test content")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();

        assertThatThrownBy(() -> announcementRepository.save(announcement))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (내용이 없음)")
    void saveAnnouncementFailureTest2() {

        User user = userRepository.getById(2L);

        Announcement announcement = Announcement.builder()
                .title("hello friends")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();

        assertThatThrownBy(() -> announcementRepository.save(announcement))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
