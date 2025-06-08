package com.source.timetable.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.source.timetable.enums.RequestStatus;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate classesDate;

    @Column(nullable = false)
    private LocalTime classesTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    @JsonIgnore
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;

    @Column(name = "description", nullable = true)
    private String description;


    public Request() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getClassesDate() {
        return classesDate;
    }

    public void setClassesDate(LocalDate classesDate) {
        this.classesDate = classesDate;
    }

    public LocalTime getClassesTime() {
        return classesTime;
    }

    public void setClassesTime(LocalTime classesTime) {
        this.classesTime = classesTime;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
