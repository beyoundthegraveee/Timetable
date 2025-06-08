package com.source.timetable.controllers;

import com.source.timetable.DTOs.CreateAdminDTO;
import com.source.timetable.DTOs.CreateProfessorDTO;
import com.source.timetable.DTOs.CreateStudentDTO;
import com.source.timetable.DTOs.LoginRequest;
import com.source.timetable.enums.Role;
import com.source.timetable.models.Admin;
import com.source.timetable.models.Professor;
import com.source.timetable.models.Student;
import com.source.timetable.models.User;
import com.source.timetable.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        Optional<User> userOpt = userService.authenticate(request.getLogin(), request.getPassword());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Map<String, Object> response = new HashMap<>();

            Map<String, Object> simplifiedUser = new HashMap<>();
            simplifiedUser.put("id", user.getId());
            simplifiedUser.put("firstName", user.getFirstName());
            simplifiedUser.put("lastName", user.getLastName());
            simplifiedUser.put("login", user.getLogin());
            simplifiedUser.put("email", user.getEmail());
            simplifiedUser.put("role", user.getRole().toString());

            response.put("user", simplifiedUser);

            if (user.getRole() == Role.STUDENT && user instanceof Student student) {
                response.put("groupId", student.getGroupOfStudents().getId());
            }

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid login or password"));
        }
    }

    @PostMapping("/create/student")
    public ResponseEntity<Student> createStudent(@RequestBody CreateStudentDTO dto) {
        return ResponseEntity.ok(userService.createStudent(dto));
    }

    @PostMapping("/create/professor")
    public ResponseEntity<Professor> createProfessor(@RequestBody CreateProfessorDTO dto) {
        return ResponseEntity.ok(userService.createProfessor(dto));
    }

    @PostMapping("/create/admin")
    public ResponseEntity<Admin> createAdmin(@RequestBody CreateAdminDTO dto) {
        return ResponseEntity.ok(userService.createAdmin(dto));
    }

}
