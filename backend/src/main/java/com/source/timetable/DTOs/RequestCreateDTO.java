package com.source.timetable.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestCreateDTO {
    private LocalDate classesDate;
    private LocalTime classesTime;
    private String description;
    private int professorId;
    private int subjectId;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}