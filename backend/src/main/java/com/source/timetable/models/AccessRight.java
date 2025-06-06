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

    @ManyToOne
    @JoinColumn(name = "administrator_id", nullable = false)
    private Admin admin;

    public AccessRight(int id, String description, LocalDate creationDate) {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
    }

    public AccessRight() {

    }
}
