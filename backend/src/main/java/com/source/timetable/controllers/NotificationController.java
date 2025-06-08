package com.source.timetable.controllers;

import com.source.timetable.DTOs.ImplementNotificationDTO;
import com.source.timetable.DTOs.NotificationDTO;
import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.models.GroupOfStudents;
import com.source.timetable.models.Notification;
import com.source.timetable.models.Student;
import com.source.timetable.services.GroupService;
import com.source.timetable.services.NotificationService;
import com.source.timetable.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final StudentService studentService;

    private final NotificationService notificationService;

    private final GroupService groupService;

    @Autowired
    public NotificationController(StudentService studentService, NotificationService notificationService, GroupService groupService) {
        this.studentService = studentService;
        this.notificationService = notificationService;
        this.groupService = groupService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> all = notificationService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Notification>> getPendingNotifications() {
        List<Notification> pending = notificationService.findByStatusPosted();
        return ResponseEntity.ok(pending);
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
        notification.setCurrentGroup(dto.currentGroup);
        notification.setTargetGroup(dto.targetGroup);
        notification.setDescription(dto.description);
        notification.setNotificationStatus(dto.notificationStatus);

        Notification saved = notificationService.saveNotification(notification);
        return ResponseEntity.ok(saved);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateNotificationStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        Notification notification = notificationService.getById(id);
        if (notification == null) return ResponseEntity.notFound().build();

        notification.setNotificationStatus(NotificationStatus.valueOf(newStatus));
        notificationService.saveNotification(notification);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/approval")
    public ResponseEntity<List<ImplementNotificationDTO>> getApprovedNotifications() {
        List<Notification> approved = notificationService.findByStatus(NotificationStatus.APPROVAL);
        return ResponseEntity.ok(
                approved.stream().map(ImplementNotificationDTO::fromNotification).toList()
        );
    }

    @PatchMapping("/apply/{notificationId}")
    public ResponseEntity<Void> applyNotification(@PathVariable int notificationId) {
        Notification notification = notificationService.getById(notificationId);
        if (notification == null || notification.getNotificationStatus() != NotificationStatus.APPROVAL) {
            return ResponseEntity.badRequest().build();
        }

        Student student = notification.getStudent();
        GroupOfStudents newGroup = groupService.getById(notification.getTargetGroup());

        if (newGroup == null) {
            return ResponseEntity.notFound().build();
        }

        student.setGroupOfStudents(newGroup);
        studentService.saveStudent(student);

        notification.setNotificationStatus(NotificationStatus.DENIED);
        notificationService.saveNotification(notification);

        return ResponseEntity.ok().build();
    }


}
