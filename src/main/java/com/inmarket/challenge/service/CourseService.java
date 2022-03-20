package com.inmarket.challenge.service;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private StudentService studentService;

    public List<CourseDTO> searchCourses(Map<String, Object> querySearch) {
        return List.of();
    }

    public CourseDTO getCourseByCode(Long courseId) {
        return null;
    }

    public CourseDTO createCourse(CourseDTO student) {
        return null;
    }

    public boolean updateCourse(CourseDTO course, Long id) {
        return true;
    }

    public boolean deleteCourse(Long courseId) {
        return true;
    }

    public List<StudentDTO> getStudents(Long courseId) {
        Map<String, Object> studentQuery = new HashMap<>();
        studentQuery.put("course_code", courseId);
        return studentService.searchStudent(studentQuery);
    }
}
