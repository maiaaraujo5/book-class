package com.maiaaraujo5.bookclass.controller.createTeacher;

import com.maiaaraujo5.bookclass.controller.createTeacher.domain.CreateTeacherRequest;
import com.maiaaraujo5.bookclass.controller.shared.TeacherResponse;
import com.maiaaraujo5.bookclass.domain.teacher.Schedule;
import com.maiaaraujo5.bookclass.domain.teacher.Subject;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import com.maiaaraujo5.bookclass.service.createTeacher.CreateTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/teacher")
public class CreateTeacherController {

    private final CreateTeacherService createTeacherService;

    @Autowired
    public CreateTeacherController(CreateTeacherService createTeacherService) {
        this.createTeacherService = createTeacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody CreateTeacherRequest createTeacherRequest) {
        Teacher teacherRequest = new Teacher(createTeacherRequest.getName(),
                createTeacherRequest.getLastname(),
                createTeacherRequest.getEmail(),
                convertWorkTimeToDomain(createTeacherRequest.getWorkTimeList()),
                convertSubjectToDomain(createTeacherRequest.getSubjectList())
        );

        Teacher teacher = this.createTeacherService.execute(teacherRequest);

        TeacherResponse createTeacherResponse = new TeacherResponse(teacher);

        return new ResponseEntity<>(createTeacherResponse, HttpStatus.CREATED);
    }

    private List<WorkTime> convertWorkTimeToDomain(List<com.maiaaraujo5.bookclass.controller.shared.WorkTime> workTimeList) {
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

    private List<Subject> convertSubjectToDomain(List<com.maiaaraujo5.bookclass.controller.shared.Subject> subjectList) {

        if (CollectionUtils.isEmpty(subjectList)) {
            return new ArrayList<>();
        }


        return subjectList.stream()
                .map(subject -> new Subject(subject.getName(), subject.getTags()))
                .collect(Collectors.toList());
    }
}
