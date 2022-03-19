package com.maiaaraujo5.bookclass.controller.utils;

import com.maiaaraujo5.bookclass.domain.teacher.Schedule;
import com.maiaaraujo5.bookclass.domain.teacher.Subject;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    public static List<WorkTime> convertWorkTimeToDomain(List<com.maiaaraujo5.bookclass.controller.shared.teacher.WorkTime> workTimeList) {
        List<WorkTime> list = new ArrayList<>();

        if (CollectionUtils.isEmpty(workTimeList)) {
            return list;
        }

        workTimeList.forEach(workTime -> {
            List<Schedule> scheduleList = workTime.getScheduleList().stream().map(schedule ->
                    new Schedule(schedule.getStartHour(), schedule.getEndHour())).collect(Collectors.toList());

            WorkTime wk = new WorkTime(workTime.getWeekday(), scheduleList);

            list.add(wk);
        });

        return list;
    }

    public static List<Subject> convertSubjectToDomain(List<com.maiaaraujo5.bookclass.controller.shared.teacher.Subject> subjectList) {

        if (CollectionUtils.isEmpty(subjectList)) {
            return new ArrayList<>();
        }


        return subjectList.stream()
                .map(subject -> new Subject(subject.getName(), subject.getTags()))
                .collect(Collectors.toList());
    }
}
