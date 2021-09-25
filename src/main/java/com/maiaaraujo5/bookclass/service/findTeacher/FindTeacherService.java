package com.maiaaraujo5.bookclass.service.findTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;

public interface FindTeacherService {
    Teacher execute(String email);
}
