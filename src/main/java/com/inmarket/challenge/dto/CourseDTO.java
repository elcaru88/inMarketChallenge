package com.inmarket.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inmarket.challenge.model.Course;
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
public class CourseDTO {

    private Long courseCode;
    private String title;
    private String description;
    private List<StudentDTO> students;

    public static CourseDTO from(Course entity) {
        return CourseDTO.builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    public Course buildEntity() {
        return Course.builder()
                .title(this.title)
                .description(this.description)
                .build();
    }

    public Course buildEntity(Long courseCode) {
        return Course.builder()
                .courseCode(courseCode)
                .title(this.title)
                .description(this.description)
                .build();
    }
}
