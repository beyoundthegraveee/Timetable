package com.source.timetable.controllers;

import com.source.timetable.DTOs.NotificationDTO;
import com.source.timetable.models.Notification;
import com.source.timetable.models.Student;
import com.source.timetable.services.NotificationService;
import com.source.timetable.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final StudentService studentService;

    private final NotificationService notificationService;

    public NotificationController(StudentService studentService, NotificationService notificationService) {
        this.studentService = studentService;
        this.notificationService = notificationService;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Notification>> getNotificationsByStudent(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.getListOfNotifications());
    }

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDTO dto) {
        Student student = studentService.getStudentById(dto.studentId);
        if (student == null) {
            return ResponseEntity.badRequest().build();
        }

        Notification notification = new Notification();
        notification.setStudent(student);
        notification.setCurrentGroup(String.valueOf(dto.currentGroup));
        notification.setTargetGroup(String.valueOf(dto.targetGroup));
        notification.setDescription(dto.description);
        notification.setNotificationStatus(dto.notificationStatus);

        Notification saved = notificationService.saveNotification(notification);
        return ResponseEntity.ok(saved);
    }
}
