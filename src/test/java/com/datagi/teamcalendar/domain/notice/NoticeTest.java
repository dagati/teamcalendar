package com.datagi.teamcalendar.domain.notice;

import com.datagi.teamcalendar.domain.notice.repository.NoticeRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("develop")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    @DisplayName("공지사항 저장 실패 테스트 (제목 누락)")
    void saveNoticeFailureTest1() {

        User user = userRepository.getById(1L);
        String content = "content";

        Notice notice = Notice.builder()
                .content(content)
                .user(user)
                .build();

        assertThatThrownBy(() -> noticeRepository.save(notice))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (내용 누락)")
    void saveNoticeFailureTest2() {

        User user = userRepository.getById(1L);
        String title = "title";

        Notice notice = Notice.builder()
                .title(title)
                .user(user)
                .build();

        assertThatThrownBy(() -> noticeRepository.save(notice))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (작성자 정보 누락)")
    void saveNoticeFailureTest3() {

        String title = "title";
        String content = "content";

        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .build();

        assertThatThrownBy(() -> noticeRepository.save(notice))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("공지사항 저장 실패 테스트 (너무 긴 제목)")
    void saveNoticeFailureTest4() {

        User user = userRepository.getById(1L);
        String title = "title title title title title title title title title title title title title title title title title title";
        String content = "content";

        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();

        assertThatThrownBy(() -> noticeRepository.save(notice))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("공지사항 저장 성공 테스트")
    void saveNoticeSuccessTest() {

        User user = userRepository.getById(1L);
        String title = "title";
        String content = "content";

        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        noticeRepository.save(notice);

        assertThat(notice.getId()).isNotNull();
    }
}
