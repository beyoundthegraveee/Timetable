package com.source.timetable.repositories;

import com.source.timetable.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepo extends JpaRepository<Professor, Integer> {
}
