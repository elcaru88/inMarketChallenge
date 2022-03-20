package com.inmarket.challenge.controller;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @GetMapping
    public ResponseEntity<StudentDTO> getStudentById(@PathParam("id") Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathParam("id") Long id, @RequestBody StudentDTO student) {
        if (studentService.updateStudent(student, id)) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathParam("id") Long id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(@PathParam("id") Long id) {
        return ResponseEntity.ok(studentService.getCourses(id));
    }

    private Map<String, Object> buildQueryMap(
            Optional<String> oLastname,
            Optional<String> oFirstname,
            Optional<Long> oCoursecode) {
        Map<String, Object> queryParam = new HashMap<>();
        oLastname.ifPresent(lastname -> queryParam.put("last_name", lastname));
        oFirstname.ifPresent(firstname -> queryParam.put("firs_tname", firstname));
        oCoursecode.ifPresent(coursecode -> queryParam.put("course_code", coursecode));
        return queryParam;
    }
}
