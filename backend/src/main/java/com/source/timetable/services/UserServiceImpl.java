package com.source.timetable.services;

import com.source.timetable.DTOs.CreateAdminDTO;
import com.source.timetable.DTOs.CreateProfessorDTO;
import com.source.timetable.DTOs.CreateStudentDTO;
import com.source.timetable.DTOs.CreateUserDTO;
import com.source.timetable.enums.Role;
import com.source.timetable.models.*;
import com.source.timetable.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AdminRepo adminRepo;
    private final ProfessorRepo professorRepo;
    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;

    public UserServiceImpl(UserRepo userRepo, AdminRepo adminRepo, ProfessorRepo professorRepo, StudentRepo studentRepo, GroupRepo groupRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.professorRepo = professorRepo;
        this.studentRepo = studentRepo;
        this.groupRepo = groupRepo;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> authenticate(String login, String password) {
        return userRepo.findByLoginAndPassword(login, password);
    }

    @Override
    public Student createStudent(CreateStudentDTO dto) {
        GroupOfStudents group = groupRepo.findById(dto.groupId).orElseThrow();
        Student student = new Student();
        fillUserFields(student, dto);
        student.setRole(Role.STUDENT);
        student.setStudentNumber(dto.studentNumber);
        student.setNationality(dto.nationality);
        student.setCurrentSemester(dto.currentSemester);
        student.setGroupOfStudents(group);
        return studentRepo.save(student);
    }

    @Override
    public Professor createProfessor(CreateProfessorDTO dto) {
        Professor prof = new Professor();
        fillUserFields(prof, dto);
        prof.setRole(Role.PROFESSOR);
        prof.setPhoneNumber(dto.phoneNumber);
        prof.setAcademicDegree(dto.academicDegree);
        prof.setDepartmentName(dto.departmentName);
        prof.setEmploymentDate(dto.employmentDate);
        return professorRepo.save(prof);
    }

    @Override
    public Admin createAdmin(CreateAdminDTO dto) {
        Admin admin = new Admin();
        fillUserFields(admin, dto);
        admin.setRole(Role.ADMINISTRATOR);
        admin.setEmploymentDate(dto.employmentDate);
        admin.setAccountStatus(dto.accountStatus);
        return adminRepo.save(admin);
    }

    private void fillUserFields(User user, CreateUserDTO dto) {
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setBirthDate(dto.birthDate);
        user.setEmail(dto.email);
        user.setLogin(dto.login);
        user.setPassword(dto.password);
    }


}
