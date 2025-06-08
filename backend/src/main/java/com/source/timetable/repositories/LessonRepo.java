package com.source.timetable.repositories;

import com.source.timetable.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByGroupId(int groupId);

    List<Lesson> findByProfessorId(int professorId);

    List<Lesson> findByProfessorIdAndDateOfClass(int professorId, LocalDate date);
}
