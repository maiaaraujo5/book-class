package com.maiaaraujo5.bookclass.controller.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponse {
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String lastname;
    @JsonProperty
    private final String email;
    @JsonProperty("work_time")
    private final WorkTime workTime;

    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.lastname = teacher.getLastname();
        this.email = teacher.getEmail();
        this.workTime = new WorkTime(teacher.getWorkTime().getStartAt(), teacher.getWorkTime().getEndAt());
    }
}
