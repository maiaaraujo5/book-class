package com.maiaaraujo5.bookclass.controller.createTeacher;

import com.maiaaraujo5.bookclass.controller.createTeacher.domain.CreateTeacherRequest;
import com.maiaaraujo5.bookclass.controller.shared.TeacherResponse;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import com.maiaaraujo5.bookclass.service.createTeacher.CreateTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                new WorkTime(createTeacherRequest.getWorkTime().getStartAt(), createTeacherRequest.getWorkTime().getEndAt()));

        Teacher teacher = this.createTeacherService.execute(teacherRequest);

        TeacherResponse createTeacherResponse = new TeacherResponse(teacher);

        return new ResponseEntity<>(createTeacherResponse, HttpStatus.CREATED);
    }
}
