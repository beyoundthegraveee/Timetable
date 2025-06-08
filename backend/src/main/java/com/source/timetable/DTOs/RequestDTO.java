package com.source.timetable.DTOs;

import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Request;

public class RequestDTO {
    private int id;
    private String classesDate;
    private String classesTime;
    private String requestStatus;
    private String description;
    private int professorId;
    private int subjectId;

    public static RequestDTO fromRequest(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId());
        dto.setClassesDate(request.getClassesDate().toString());
        dto.setClassesTime(request.getClassesTime().toString());
        dto.setRequestStatus(request.getRequestStatus().toString());
        dto.setDescription(request.getDescription());
        dto.setProfessorId(request.getProfessor().getId());
        dto.setSubjectId(request.getSubject().getId());
        return dto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassesDate() {
        return classesDate;
    }

    public void setClassesDate(String classesDate) {
        this.classesDate = classesDate;
    }

    public String getClassesTime() {
        return classesTime;
    }

    public void setClassesTime(String classesTime) {
        this.classesTime = classesTime;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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