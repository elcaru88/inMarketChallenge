package com.inmarket.challenge.service;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private CourseService courseService;

    public StudentDTO getStudentById(Long studentId) {
        return null;
    }

    public List<StudentDTO> searchStudent(Map<String, Object> querySearch) {
        return null;
    }

    public StudentDTO createStudent(StudentDTO student) {
        return student;
    }

    public boolean updateStudent(StudentDTO student, Long studentId) {
        return true;
    }

    public boolean deleteStudent(Long studentId) {
        return true;
    }

    public List<CourseDTO> getCourses(Long studentId) {
        Map<String, Object> courseQuery = new HashMap<>();
        courseQuery.put("student_id", studentId);
        return courseService.searchCourses(courseQuery);
    }
}
