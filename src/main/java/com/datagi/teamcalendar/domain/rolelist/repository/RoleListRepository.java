package com.datagi.teamcalendar.domain.rolelist.repository;

import com.datagi.teamcalendar.domain.rolelist.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleListRepository extends JpaRepository<RoleList,Long> {
}
