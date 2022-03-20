package com.inmarket.challenge.service;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> searchStudent(Map<String, Object> querySearch) {
        return null;
    }

    public StudentDTO getStudentById(Long studentId) {
        return StudentDTO.from(studentRepository.getById(studentId));
    }

    public StudentDTO createStudent(StudentDTO student) {
        student.setStudentId(studentRepository.save(student.buildEntity()).getStudentId());
        return student;
    }

    public void updateStudent(StudentDTO student, Long studentId) {
        studentRepository.save(student.buildEntity(studentId));
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<CourseDTO> getCourses(Long studentId) {
        Map<String, Object> courseQuery = new HashMap<>();
        courseQuery.put("student_id", studentId);
        return courseService.searchCourses(courseQuery);
    }
}
