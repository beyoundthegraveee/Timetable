package com.source.timetable.services;

import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.models.Notification;
import com.source.timetable.repositories.NotificationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    @Override
    public List<Notification> findByStatusPosted() {
        return notificationRepo.findByNotificationStatus(NotificationStatus.POSTED);
    }

    @Override
    public Notification getById(int id) {
        return notificationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Notification> findByStatus(NotificationStatus notificationStatus) {
        return notificationRepo.findByNotificationStatus(notificationStatus);
    }

}
