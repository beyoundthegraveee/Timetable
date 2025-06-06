package com.source.timetable.repositories;

import com.source.timetable.models.AccessRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRightRepo extends JpaRepository<AccessRight, Integer> {
}
