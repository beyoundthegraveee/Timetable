package com.source.timetable.controllers;

import com.source.timetable.DTOs.GroupDTO;
import com.source.timetable.DTOs.StudentDTO;
import com.source.timetable.models.GroupOfStudents;
import com.source.timetable.models.Student;
import com.source.timetable.services.GroupService;
import com.source.timetable.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
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

}
