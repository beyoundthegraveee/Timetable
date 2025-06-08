package com.source.timetable.models;

import com.source.timetable.enums.SubjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String abbreviation;

    @Column(nullable=false, name = "number_of_ect")
    private int numberOfECTS;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    public Subject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getNumberOfECTS() {
        return numberOfECTS;
    }

    public void setNumberOfECTS(int numberOfECTS) {
        this.numberOfECTS = numberOfECTS;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public String getFullDescription() {
        return String.format(
                "Przedmiot: %s (%s), Typ: %s, Punkty ECTS: %d",
                name, abbreviation, subjectType.name(), numberOfECTS
        );
    }

    public static int generateECTSForType(SubjectType type) {
        int min, max;
        switch (type) {
            case MANDATORY -> {
                min = 4;
                max = 8;
            }
            case ADDITIONAL -> {
                min = 2;
                max = 5;
            }
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
