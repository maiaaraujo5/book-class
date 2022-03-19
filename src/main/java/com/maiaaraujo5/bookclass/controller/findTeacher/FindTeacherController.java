package com.maiaaraujo5.bookclass.controller.findTeacher;

import com.maiaaraujo5.bookclass.controller.shared.teacher.TeacherResponse;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.service.findTeacher.FindTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/teacher")
public class FindTeacherController {

    private final FindTeacherService findTeacherService;

    @Autowired
    public FindTeacherController(FindTeacherService findTeacherService) {
        this.findTeacherService = findTeacherService;
    }

    @GetMapping
    public ResponseEntity<TeacherResponse> findTeacher(@Param("email") String email) {
        Teacher teacher = this.findTeacherService.execute(email);
        TeacherResponse teacherResponse = new TeacherResponse(teacher);

        return new ResponseEntity<>(teacherResponse, HttpStatus.OK);
    }
}
