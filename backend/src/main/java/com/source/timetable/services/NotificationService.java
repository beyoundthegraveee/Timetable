package com.source.timetable.services;

import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.models.Notification;

import java.util.List;

public interface NotificationService {

    Notification saveNotification(Notification notification);

    List<Notification> findAll();

    List<Notification> findByStatusPosted();

    Notification getById(int id);

    List<Notification> findByStatus(NotificationStatus notificationStatus);
}
