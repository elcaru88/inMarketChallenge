package com.inmarket.challenge.controller;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentRest {

    @GetMapping
    public ResponseEntity<List<StudentDTO>> searchStudent(
            @RequestParam(value = "lastname", required = false) Optional<String> lastname,
            @RequestParam(value = "firstname", required = false) Optional<String> firstname,
            @RequestParam(value = "coursecode", required = false) Optional<Long> coursecode) {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping
    public ResponseEntity<StudentDTO> getCourseByCode(@PathParam("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathParam("id") Long id, @RequestBody StudentDTO student) {
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathParam("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(@PathParam("id") Long id) {
        return ResponseEntity.ok(List.of());
    }
}
