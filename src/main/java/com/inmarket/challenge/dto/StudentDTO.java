package com.inmarket.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inmarket.challenge.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Long studentId;
    private String firstName;
    private String lastName;
    private List<CourseDTO> courses;

    public static StudentDTO from(Student entity) {
        return StudentDTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

    public Student buildEntity() {
        return Student.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }

    public Student buildEntity(Long studentId) {
        return Student.builder()
                .studentId(studentId)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }

}
