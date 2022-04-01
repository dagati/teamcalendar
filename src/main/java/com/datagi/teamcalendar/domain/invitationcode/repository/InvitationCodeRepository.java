package com.datagi.teamcalendar.domain.invitationcode.repository;

import com.datagi.teamcalendar.domain.invitationcode.InvitationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationCodeRepository extends JpaRepository<InvitationCode, Long> {
}
