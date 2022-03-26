package com.datagi.teamcalendar.domain.schedule.repository;

import com.datagi.teamcalendar.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
