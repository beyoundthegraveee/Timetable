package com.source.timetable.models;

import com.source.timetable.enums.NotificationStatus;
import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Student student;

    @Column(nullable = false)
    private GroupOfStudents groupOfStudents;

    @Column(nullable = false)
    private GroupOfStudents targetGroup;

    @Column(nullable = false)
    private NotificationStatus notificationStatus;

    @Column(nullable = false)
    private String description;






}
