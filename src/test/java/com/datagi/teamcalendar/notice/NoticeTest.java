package com.datagi.teamcalendar.notice;

import com.datagi.teamcalendar.domain.notice.Notice;
import com.datagi.teamcalendar.domain.notice.repository.NoticeRepository;
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
class NoticeTest {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoticeTest(
            NoticeRepository noticeRepository,
            UserRepository userRepository
    ) {
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("공지사항 저장 성공 테스트")
    void saveNoticeTest() {

        User user = userRepository.getById(1L);

        Notice notice = Notice.builder()
                .title("hello friends")
                .content("test content")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();
        noticeRepository.save(notice);

        assertThat(notice.getId()).isNotNull();
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (제목이 없음)")
    void saveNoticeFailureTest1() {

        User user = userRepository.getById(1L);

        Notice notice = Notice.builder()
                .content("test content")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();

        assertThatThrownBy(() -> noticeRepository.save(notice))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (내용이 없음)")
    void saveNoticeFailureTest2() {

        User user = userRepository.getById(2L);

        Notice notice = Notice.builder()
                .title("hello friends")
                .createdDatetime(LocalDateTime.now())
                .user(user)
                .build();

        assertThatThrownBy(() -> noticeRepository.save(notice))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
