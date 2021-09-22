package com.maiaaraujo5.bookclass.controller.createTeacher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import lombok.Getter;

@Getter
public class CreateTeacherResponse {
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String lastname;
    @JsonProperty
    private final String email;

    public CreateTeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.lastname = teacher.getLastname();
        this.email = teacher.getEmail();
    }
}

