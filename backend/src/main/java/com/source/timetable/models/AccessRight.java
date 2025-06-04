package com.source.timetable.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="access_rights")
public class AccessRight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate creationDate;

    public AccessRight(int id, String description, LocalDate creationDate) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
    }

    public AccessRight() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
