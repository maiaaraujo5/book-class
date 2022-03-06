package com.maiaaraujo5.bookclass.controller.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @JsonProperty("workTime")
    private final List<WorkTime> workTimeList;

    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.lastname = teacher.getLastname();
        this.email = teacher.getEmail();
        this.workTimeList = convertWorkTimeList(teacher.getWorkTimeList());
    }

    private List<WorkTime> convertWorkTimeList(List<com.maiaaraujo5.bookclass.domain.teacher.WorkTime> workTimeList) {
        List<WorkTime> list = new ArrayList<>();

        workTimeList.forEach(workTime -> {
            List<com.maiaaraujo5.bookclass.controller.shared.Schedule> scheduleList = workTime.getScheduleList().stream().map(schedule ->
                    new Schedule(schedule.getStartHour(), schedule.getEndHour())).collect(Collectors.toList());

            WorkTime wk = new WorkTime(workTime.getWeekday(), scheduleList);

            list.add(wk);
        });

        return list;
    }
}
