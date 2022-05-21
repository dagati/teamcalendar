package com.datagi.teamcalendar.domain.position.repository;

import com.datagi.teamcalendar.domain.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
