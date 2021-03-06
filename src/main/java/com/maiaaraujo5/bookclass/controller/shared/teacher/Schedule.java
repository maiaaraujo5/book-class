package com.maiaaraujo5.bookclass.controller.shared.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    @JsonProperty("startHour")
    private final int startHour;
    @JsonProperty("endHour")
    private final int endHour;
}
