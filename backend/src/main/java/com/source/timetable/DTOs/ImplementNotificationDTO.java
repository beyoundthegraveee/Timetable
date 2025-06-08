package com.source.timetable.DTOs;

import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.models.Notification;

public class ImplementNotificationDTO {
    public int id;
    public int studentId;
    public String studentName;
    public int currentGroup;
    public int targetGroup;
    public String description;
    public NotificationStatus notificationStatus;

    public static ImplementNotificationDTO fromNotification(Notification notification) {
        ImplementNotificationDTO dto = new ImplementNotificationDTO();
        dto.id = notification.getId();
        dto.studentId = notification.getStudent().getId();
        dto.studentName = notification.getStudent().getFirstName() + " " +
                notification.getStudent().getLastName();
        dto.currentGroup = Integer.parseInt(notification.getCurrentGroup());
        dto.targetGroup = Integer.parseInt(notification.getTargetGroup());
        dto.description = notification.getDescription();
        dto.notificationStatus = notification.getNotificationStatus();
        return dto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
