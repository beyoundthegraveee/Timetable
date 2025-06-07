package com.source.timetable.services;

import com.source.timetable.models.Notification;
import com.source.timetable.repositories.NotificationRepo;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;

    public NotificationServiceImpl(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }


    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepo.save(notification);
    }
}
