package com.maiaaraujo5.bookclass.service.updateTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;

public interface UpdateTeacherService {
    Teacher execute(Teacher teacher, String userId);
}
