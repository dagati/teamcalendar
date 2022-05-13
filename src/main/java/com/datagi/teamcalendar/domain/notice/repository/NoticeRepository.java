package com.datagi.teamcalendar.domain.notice.repository;

import com.datagi.teamcalendar.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
