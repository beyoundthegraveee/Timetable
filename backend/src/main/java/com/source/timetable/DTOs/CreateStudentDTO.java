package com.source.timetable.DTOs;

import com.source.timetable.enums.Role;

import java.time.LocalDate;

public class CreateStudentDTO extends CreateUserDTO{
    public String studentNumber;
    public String nationality;
    public int currentSemester;
    public int groupId;


    public CreateStudentDTO(String firstName, String lastName, LocalDate birthDate, String email, String login, String password, Role role, String studentNumber, String nationality, int currentSemester, int groupId) {
        super(firstName, lastName, birthDate, email, login, password, role);
        this.studentNumber = studentNumber;
        this.nationality = nationality;
        this.currentSemester = currentSemester;
        this.groupId = groupId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
