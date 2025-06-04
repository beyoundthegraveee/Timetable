package com.source.timetable.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User{

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private int currentSemester;

    private List<Notification> listOfNotifications;

    public Student() {
        super();
    }

    public Student(int id, String firstName, String lastName, LocalDate birthDate, String email, String login, String password, String studentNumber, String nationality, int currentSemester) {
        super(id, firstName, lastName, birthDate, email, login, password);
        this.studentNumber = studentNumber;
        this.nationality = nationality;
        this.currentSemester = currentSemester;
        this.listOfNotifications = new ArrayList<>();
    }


}
