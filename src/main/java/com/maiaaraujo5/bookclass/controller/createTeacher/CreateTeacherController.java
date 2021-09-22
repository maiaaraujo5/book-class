package com.maiaaraujo5.bookclass.controller.createTeacher;

import com.maiaaraujo5.bookclass.controller.createTeacher.domain.CreateTeacherRequest;
import com.maiaaraujo5.bookclass.controller.createTeacher.domain.CreateTeacherResponse;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.service.createTeacher.CreateTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/teacher")
public class CreateTeacherController {

    private final CreateTeacher createTeacher;

    @Autowired
    public CreateTeacherController(CreateTeacher createTeacher) {
        this.createTeacher = createTeacher;
    }

    @PostMapping
    public ResponseEntity<CreateTeacherResponse> createTeacher(@RequestBody CreateTeacherRequest createTeacherRequest) {
        Teacher teacherRequest = new Teacher(createTeacherRequest.getName(),
                createTeacherRequest.getLastname(),
                createTeacherRequest.getEmail());

        Teacher teacher = this.createTeacher.Execute(teacherRequest);

        CreateTeacherResponse createTeacherResponse = new CreateTeacherResponse(teacher);

        return new ResponseEntity<>(createTeacherResponse, HttpStatus.CREATED);
    }
}
