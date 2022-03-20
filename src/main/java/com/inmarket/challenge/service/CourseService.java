package com.inmarket.challenge.service;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> searchCourses(Map<String, Object> querySearch) {
        return List.of();
    }

    public CourseDTO getCourseByCode(Long courseId) {
        return CourseDTO.from(courseRepository.getById(courseId));
    }

    public CourseDTO createCourse(CourseDTO course) {
        course.setCourseCode(courseRepository.save(course.buildEntity()).getCourseCode());
        return course;
    }

    public void updateCourse(CourseDTO course, Long courseCode) {
        courseRepository.save(course.buildEntity(courseCode));
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<StudentDTO> getStudents(Long courseId) {
        Map<String, Object> studentQuery = new HashMap<>();
        studentQuery.put("course_code", courseId);
        return studentService.searchStudent(studentQuery);
    }
}
