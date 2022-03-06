package com.maiaaraujo5.bookclass.repository.teacher.model;

import com.maiaaraujo5.bookclass.domain.teacher.Schedule;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class WorkTimeDocument {
    @Field("weekday")
    String weekday;
    @Field("schedules")
    List<ScheduleDocument> scheduleDocumentList;

    public WorkTimeDocument(WorkTime workTime) {
        this.weekday = workTime.getWeekday();
        this.scheduleDocumentList = convertScheduleList(workTime.getScheduleList());
    }

    private List<ScheduleDocument> convertScheduleList(List<Schedule> scheduleList) {
        return scheduleList.stream().map(schedule ->
                new ScheduleDocument(schedule.getStartHour(), schedule.getEndHour())).collect(Collectors.toList());
    }
}
