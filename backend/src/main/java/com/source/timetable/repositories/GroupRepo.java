package com.source.timetable.repositories;


import com.source.timetable.models.GroupOfStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<GroupOfStudents, Integer> {

    GroupOfStudents findByName(String name);

}
