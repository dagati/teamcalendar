package com.datagi.teamcalendar.domain.teamrelation.repository;

import com.datagi.teamcalendar.domain.teamrelation.TeamRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRelationRepository extends JpaRepository<TeamRelation, Long> {
}
