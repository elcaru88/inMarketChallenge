package com.inmarket.challenge.service;

import com.inmarket.challenge.dto.CourseDTO;
import com.inmarket.challenge.dto.StudentDTO;
import com.inmarket.challenge.model.Course;
import com.inmarket.challenge.model.Student;
import com.inmarket.challenge.repository.CourseRepository;
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
public class CourseService {

    @PersistenceContext
    private EntityManager entityManager;

    @Lazy
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> searchCourses(Map<String, Object> querySearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);
        Root<Course> courses = query.from(Course.class);
        List<Predicate> predicates = new ArrayList<>();
        querySearch.forEach((key, value) -> predicates.add(cb.equal(courses.get(key), value)));
        query.select(courses).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Course> q = entityManager.createQuery(query);
        return q.getResultList().stream().map(CourseDTO::from).collect(Collectors.toList());
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
