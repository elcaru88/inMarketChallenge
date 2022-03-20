package com.inmarket.challenge.controller;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentRest {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> searchStudent(
            @RequestParam(value = "lastname", required = false) Optional<String> lastname,
            @RequestParam(value = "firstname", required = false) Optional<String> firstname,
            @RequestParam(value = "coursecode", required = false) Optional<Long> coursecode) {
        return ResponseEntity.ok(studentService.searchStudent(buildQueryMap(lastname, firstname, coursecode)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDTO student) {
        studentService.updateStudent(student, id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.getCourses(id));
    }

    private Map<String, Object> buildQueryMap(
            Optional<String> oLastname,
            Optional<String> oFirstname,
            Optional<Long> oCoursecode) {
        Map<String, Object> queryParam = new HashMap<>();
        oLastname.ifPresent(lastname -> queryParam.put("lastName", lastname));
        oFirstname.ifPresent(firstname -> queryParam.put("firstName", firstname));
        oCoursecode.ifPresent(coursecode -> queryParam.put("courseCode", coursecode));
        return queryParam;
    }
}
