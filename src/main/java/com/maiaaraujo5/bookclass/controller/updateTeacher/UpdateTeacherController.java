package com.maiaaraujo5.bookclass.controller.updateTeacher;

import com.maiaaraujo5.bookclass.controller.shared.teacher.TeacherResponse;
import com.maiaaraujo5.bookclass.controller.updateTeacher.domain.UpdateTeacherRequest;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.service.updateTeacher.UpdateTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.maiaaraujo5.bookclass.controller.utils.Converters.convertSubjectToDomain;
import static com.maiaaraujo5.bookclass.controller.utils.Converters.convertWorkTimeToDomain;

@RestController
@RequestMapping("/v1/teacher")
public class UpdateTeacherController {

    private final UpdateTeacherService updateTeacherService;

    @Autowired
    public UpdateTeacherController(UpdateTeacherService updateTeacherService) {
        this.updateTeacherService = updateTeacherService;
    }

    @PutMapping
    public ResponseEntity<TeacherResponse> updateTeacher(@RequestBody UpdateTeacherRequest updateTeacherRequest, @RequestParam(name = "id") String userId) {
        Teacher teacher = new Teacher(updateTeacherRequest.getName(),
                updateTeacherRequest.getLastname(),
                updateTeacherRequest.getEmail(),
                convertWorkTimeToDomain(updateTeacherRequest.getWorkTimeList()),
                convertSubjectToDomain(updateTeacherRequest.getSubjectList()));

        teacher = this.updateTeacherService.execute(teacher, userId);

        TeacherResponse updatedTeacherResponse = new TeacherResponse(teacher);

        return new ResponseEntity<>(updatedTeacherResponse, HttpStatus.OK);
    }
}
