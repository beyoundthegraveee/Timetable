package com.source.timetable.repositories;

import com.source.timetable.models.GroupOfStudents;
import com.source.timetable.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByGroupOfStudents(GroupOfStudents group);

    Student findStudentById(int id);


}
