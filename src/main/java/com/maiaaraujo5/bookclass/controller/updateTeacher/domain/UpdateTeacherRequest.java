package com.maiaaraujo5.bookclass.controller.updateTeacher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiaaraujo5.bookclass.controller.shared.teacher.Subject;
import com.maiaaraujo5.bookclass.controller.shared.teacher.WorkTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateTeacherRequest {
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
