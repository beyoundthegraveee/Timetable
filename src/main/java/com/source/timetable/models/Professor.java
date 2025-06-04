package com.source.timetable.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor extends User{

    @Column(nullable = false)
    private LocalDate employmentDate;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String academicDegree;

    @Column(nullable = false)
    private String departmentName;

    private List<Request> requests;

    public Professor() {
        super();
    }

    public Professor(int id, String firstName, String lastName, LocalDate birthDate, String email, String login, String password, LocalDate employmentDate, String phoneNumber, String academicDegree, String departmentName) {
        super(id, firstName, lastName, birthDate, email, login, password);
        this.employmentDate = employmentDate;
        this.phoneNumber = phoneNumber;
        this.academicDegree = academicDegree;
        this.departmentName = departmentName;
        this.requests = new ArrayList<>();
    }


}
