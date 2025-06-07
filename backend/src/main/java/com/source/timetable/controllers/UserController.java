package com.source.timetable.controllers;

import com.source.timetable.DTOs.LoginRequest;
import com.source.timetable.models.Student;
import com.source.timetable.models.User;
import com.source.timetable.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Optional<User> user = userService.authenticate(request.getLogin(), request.getPassword());

        if (user.isPresent()) {
            User u = user.get();
            if (u instanceof Student student) {
                int groupId = student.getGroupOfStudents().getId();
                return ResponseEntity.ok().body(Map.of(
                        "user", student,
                        "groupId", groupId
                ));
            }
            return ResponseEntity.ok().body(Map.of("user", u));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid login or password"));
        }
    }

}
