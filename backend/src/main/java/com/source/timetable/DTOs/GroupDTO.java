package com.source.timetable.DTOs;


public class GroupDTO {
    private int id;
    private String name;
    private int maxCountOfStudents;

    public GroupDTO() {}

    public GroupDTO(int id, String name, int maxCountOfStudents) {
        this.id = id;
        this.name = name;
        this.maxCountOfStudents = maxCountOfStudents;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMaxCountOfStudents() { return maxCountOfStudents; }
    public void setMaxCountOfStudents(int maxCountOfStudents) { this.maxCountOfStudents = maxCountOfStudents; }
}