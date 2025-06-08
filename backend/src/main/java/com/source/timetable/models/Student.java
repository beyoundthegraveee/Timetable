package com.source.timetable.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private int currentSemester;

    private static int maksSemester = 7;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupOfStudents groupOfStudents;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Notification> listOfNotifications;

    public Student() {
        super();
    }

    @Override
    public String getUserTypeInfo(){
        return "Student " + this.getFirstName() + " " + this.getLastName() + " with student number: " + studentNumber + " is " + nationality;
    };

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
        if (currentSemester < 1 || currentSemester > maksSemester) {
            throw new IllegalArgumentException("Semester must be between 1 and " + maksSemester);
        }
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

    public static int getMaksSemester() {
        return maksSemester;
    }

    public static void setMaksSemester(int maks) {
        if (maks < 4) {
            throw new IllegalArgumentException("Maximum semester must be at least 4");
        }
        maksSemester = maks;
    }
}
