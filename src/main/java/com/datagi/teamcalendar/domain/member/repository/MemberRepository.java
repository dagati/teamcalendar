package com.datagi.teamcalendar.domain.member.repository;

import com.datagi.teamcalendar.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
