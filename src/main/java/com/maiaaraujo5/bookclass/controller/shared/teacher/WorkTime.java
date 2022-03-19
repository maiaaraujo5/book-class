package com.maiaaraujo5.bookclass.controller.shared.teacher;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WorkTime {

    @JsonProperty
    private final String weekday;

    @JsonProperty("schedules")
    private final List<Schedule> scheduleList;
}
