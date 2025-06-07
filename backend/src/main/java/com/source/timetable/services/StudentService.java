package com.source.timetable.services;

import com.source.timetable.models.GroupOfStudents;
import com.source.timetable.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudentsByGroup(GroupOfStudents group);

    Student getStudentById(int id);


}
