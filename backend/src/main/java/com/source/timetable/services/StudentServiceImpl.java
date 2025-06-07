package com.source.timetable.services;

import com.source.timetable.models.GroupOfStudents;
import com.source.timetable.models.Student;
import com.source.timetable.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public List<Student> getStudentsByGroup(GroupOfStudents group) {
        return studentRepo.findStudentsByGroupOfStudents(group);
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepo.findStudentById(id);
    }
}
