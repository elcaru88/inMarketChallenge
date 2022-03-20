package com.inmarket.challenge.controller;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseRest {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> searchCourses(
            @RequestParam(value = "title", required = false) Optional<String> title,
            @RequestParam(value = "description", required = false) Optional<String> description,
            @RequestParam(value = "studentid", required = false) Optional<Long> studentid) {
        return ResponseEntity.ok(courseService.searchCourses(buildQueryMap(title, description, studentid)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseByCode(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getCourseByCode(id));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO course, @PathParam("id") Long id) {
        courseService.updateCourse(course, id);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getStudents(id));
    }

    private Map<String, Object> buildQueryMap(
            Optional<String> oTitle,
            Optional<String> oDescription,
            Optional<Long> oStudentid) {
        Map<String, Object> queryParam = new HashMap<>();
        oTitle.ifPresent(title -> queryParam.put("title", title));
        oDescription.ifPresent(description -> queryParam.put("description", description));
        oStudentid.ifPresent(studentId -> queryParam.put("studentId", studentId));
        return queryParam;
    }
}
