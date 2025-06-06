package com.source.timetable.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String academicYear;

    @Column(nullable = false, name = "number_of_semester")
    private int numberOfSemester;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate sessionStartDate;

    @Column(nullable = false)
    private LocalDate sessionEndDate;

    public Semester(int id, String academicYear, int numberOfSemester, LocalDate startDate, LocalDate endDate, LocalDate sessionStartDate, LocalDate sessionEndDate) {
        this.id = id;
        this.academicYear = academicYear;
        this.numberOfSemester = numberOfSemester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
    }

    public Semester() {

    }
}
