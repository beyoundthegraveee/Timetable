package com.source.timetable.services;


import com.source.timetable.DTOs.CreateAdminDTO;
import com.source.timetable.DTOs.CreateProfessorDTO;
import com.source.timetable.DTOs.CreateStudentDTO;
import com.source.timetable.models.Admin;
import com.source.timetable.models.Professor;
import com.source.timetable.models.Student;
import com.source.timetable.models.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> getAllUsers();

    Optional<User> authenticate(String login, String password);


    Student createStudent(CreateStudentDTO dto);

    Professor createProfessor(CreateProfessorDTO dto);

    Admin createAdmin(CreateAdminDTO dto);
}
