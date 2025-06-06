package com.source.timetable.DTOs;

public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String studentNumber;

    public StudentDTO() {}

    public StudentDTO(int id, String firstName, String lastName, String studentNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStudentNumber() { return studentNumber; }

    public void setId(int id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
}