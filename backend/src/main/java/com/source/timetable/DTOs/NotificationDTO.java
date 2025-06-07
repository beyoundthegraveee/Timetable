package com.source.timetable.DTOs;

import com.source.timetable.enums.NotificationStatus;

public class NotificationDTO {
    public int studentId;
    public int currentGroup;
    public int targetGroup;
    public String description;
    public NotificationStatus notificationStatus;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}