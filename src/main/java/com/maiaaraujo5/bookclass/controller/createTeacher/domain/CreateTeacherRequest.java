package com.maiaaraujo5.bookclass.controller.createTeacher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiaaraujo5.bookclass.controller.shared.teacher.Subject;
import com.maiaaraujo5.bookclass.controller.shared.teacher.WorkTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateTeacherRequest {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String lastname;
    @JsonProperty
    private final String email;
    @JsonProperty("workTime")
    private final List<WorkTime> workTimeList;
    @JsonProperty("subjects")
    private final List<Subject> subjectList;
}
