package com.datagi.teamcalendar.domain.participant.repository;

import com.datagi.teamcalendar.domain.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
