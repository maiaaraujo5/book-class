package com.maiaaraujo5.bookclass.controller.createTeacher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTeacherRequest {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String lastname;
    @JsonProperty
    private final String email;
}
