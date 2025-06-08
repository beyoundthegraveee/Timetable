package com.source.timetable.controllers;

import com.source.timetable.DTOs.GroupDTO;
import com.source.timetable.DTOs.StudentDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    private final StudentService studentService;

    private final NotificationService notificationService;

    @Autowired
    public GroupController(GroupService groupService, StudentService studentService, NotificationService notificationService) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<GroupDTO> getAllGroups() {
        List<GroupOfStudents> groups = groupService.getAllGroups();
        return groups.stream()
                .map(group -> new GroupDTO(group.getId(), group.getName(), group.getMaxCountOfStudents()))
                .collect(Collectors.toList());
    }

    @GetMapping("/name/{groupName}")
    public ResponseEntity<List<StudentDTO>> getStudentsByGroupName(@PathVariable String groupName) {
        GroupOfStudents group = groupService.getGroupByName(groupName);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDTO> studentDTOs = studentService.getStudentsByGroup(group).stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getStudentNumber()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/id/{groupName}")
    public ResponseEntity<Integer> getGroupIdByGroupName(@PathVariable String groupName) {
        GroupOfStudents group = groupService.getGroupByName(groupName);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group.getId());
    }

    @PostMapping("/transfer-student")
    public ResponseEntity<Void> transferStudent(@RequestBody Map<String, String> body) {
        try {
            int studentId = Integer.parseInt(body.get("studentId"));
            int fromGroupId = Integer.parseInt(body.get("fromGroupId"));
            int toGroupId = Integer.parseInt(body.get("toGroupId"));
            Student student = studentService.getStudentById(studentId);
            GroupOfStudents newGroup = groupService.getById(toGroupId);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            student.setGroupOfStudents(newGroup);
            studentService.save(student);

            Notification notification = notificationService.findByStudentAndGroups(student, fromGroupId, toGroupId);
            if (notification != null) {
                notification.setNotificationStatus(NotificationStatus.IMPLEMENTED);
                notificationService.saveNotification(notification);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
