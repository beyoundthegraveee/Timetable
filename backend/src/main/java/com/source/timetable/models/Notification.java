package com.source.timetable.models;

import com.source.timetable.enums.NotificationStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String currentGroup;

    @Column(nullable = false)
    private String targetGroup;

    @Column(nullable = false)
    private NotificationStatus notificationStatus;

    @Column(nullable = false)
    private String description;


    public Notification() {

    }
}
