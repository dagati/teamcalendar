package com.datagi.teamcalendar.domain.memberlist.repository;

import com.datagi.teamcalendar.domain.memberlist.MemberList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberListRepository extends JpaRepository<MemberList, Long> {
}
