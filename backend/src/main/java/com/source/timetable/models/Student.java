package com.source.timetable.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User{

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private int currentSemester;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupOfStudents groupOfStudents;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> listOfNotifications = new ArrayList<>();

    public Student() {
        super();
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

    public GroupOfStudents getGroupOfStudents() {
        return groupOfStudents;
    }

    public void setGroupOfStudents(GroupOfStudents groupOfStudents) {
        this.groupOfStudents = groupOfStudents;
    }

    public List<Notification> getListOfNotifications() {
        return listOfNotifications;
    }

    public void setListOfNotifications(List<Notification> listOfNotifications) {
        this.listOfNotifications = listOfNotifications;
    }
}
