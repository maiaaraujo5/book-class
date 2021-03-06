package com.maiaaraujo5.bookclass.domain.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WorkTime {
    private final String weekday;
    private final List<Schedule> scheduleList;
}
