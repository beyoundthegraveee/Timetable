package com.source.timetable.repositories;

import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.models.Notification;
import com.source.timetable.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {


    List<Notification> findByNotificationStatus(NotificationStatus notificationStatus);

    Notification findByStudentAndCurrentGroupAndTargetGroup(Student student, int currentGroup, int targetGroup);
}
