package com.source.timetable.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "semester")
    private List<Course> courses;

    public Semester(int id, String academicYear, int numberOfSemester, LocalDate startDate, LocalDate endDate, LocalDate sessionStartDate, LocalDate sessionEndDate) {
        this.id = id;
        this.academicYear = academicYear;
        this.numberOfSemester = numberOfSemester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Semester() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public int getNumberOfSemester() {
        return numberOfSemester;
    }

    public void setNumberOfSemester(int numberOfSemester) {
        this.numberOfSemester = numberOfSemester;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(LocalDate sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public LocalDate getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(LocalDate sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }
}
