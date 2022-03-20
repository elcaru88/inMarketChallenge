package com.inmarket.challenge.controller;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseRest {

    @GetMapping
    public ResponseEntity<List<CourseDTO>> searchCourses(
            @RequestParam(value = "title", required = false) Optional<String> title,
            @RequestParam(value = "description", required = false) Optional<String> description,
            @RequestParam(value = "studentid", required = false) Optional<Long> studentid) {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping
    public ResponseEntity<CourseDTO> getCourseByCode(@PathParam("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createStudent(@RequestBody CourseDTO student) {
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateStudent(@PathParam("id") Long id, @RequestBody CourseDTO student) {
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathParam("id") Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@PathParam("id") Long id) {
        return ResponseEntity.ok(List.of());
    }
}
