package com.maiaaraujo5.bookclass.controller.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Schedule {
    @JsonProperty("startHour")
    int startHour;
    @JsonProperty("endHour")
    int endHour;
}
