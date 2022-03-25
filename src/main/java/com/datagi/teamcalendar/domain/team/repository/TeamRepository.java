package com.datagi.teamcalendar.domain.team.repository;

import com.datagi.teamcalendar.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
