package com.source.timetable.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Student student;

    @Column(nullable = false)
    private int currentGroup;

    @Column(nullable = false)
    private int targetGroup;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    @Column(nullable = true)
    private String description;


    public Notification() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(int currentGroup) {
        this.currentGroup = currentGroup;
    }

    public int getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(int targetGroup) {
        this.targetGroup = targetGroup;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", student=" + student.toString() +
                ", currentGroup='" + currentGroup + '\'' +
                ", targetGroup='" + targetGroup + '\'' +
                ", notificationStatus=" + notificationStatus +
                ", description='" + description + '\'' +
                '}';
    }
}
