package com.source.timetable.DTOs;

import com.source.timetable.enums.Role;

import java.time.LocalDate;

public class CreateProfessorDTO extends CreateUserDTO{
    public LocalDate employmentDate;
    public String phoneNumber;
    public String academicDegree;
    public String departmentName;

    public CreateProfessorDTO(String firstName, String lastName, LocalDate birthDate, String email, String login, String password, Role role, LocalDate employmentDate, String phoneNumber, String academicDegree, String departmentName) {
        super(firstName, lastName, birthDate, email, login, password, role);
        this.employmentDate = employmentDate;
        this.phoneNumber = phoneNumber;
        this.academicDegree = academicDegree;
        this.departmentName = departmentName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
