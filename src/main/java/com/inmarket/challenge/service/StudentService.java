package com.inmarket.challenge.service;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.exceptions.ChallengeEntityNotFoundException;
import com.inmarket.challenge.model.Student;
import com.inmarket.challenge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Lazy
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> searchStudent(Map<String, Object> querySearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> students = query.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();
        querySearch.forEach((key, value) -> predicates.add(cb.equal(students.get(key), value)));
        query.select(students).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Student> q = entityManager.createQuery(query);
        return q.getResultList().stream().map(StudentDTO::from).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long studentId) {
        return StudentDTO.from(studentRepository.findById(studentId).orElseThrow(ChallengeEntityNotFoundException::new));
    }

    public StudentDTO createStudent(StudentDTO student) {
        student.setStudentId(studentRepository.saveAndFlush(student.buildEntity()).getStudentId());
        return student;
    }

    public void updateStudent(StudentDTO student, Long studentId) {
        studentRepository.findById(studentId).orElseThrow(ChallengeEntityNotFoundException::new);
        studentRepository.saveAndFlush(student.buildEntity(studentId));
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
