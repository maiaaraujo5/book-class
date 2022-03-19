package com.maiaaraujo5.bookclass.repository.teacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;

import java.util.Optional;


public interface TeacherRepository {
    void Save(Teacher teacher);

    Optional<Teacher> FindByEmail(String email);

    Optional<Teacher> FindById(String id);
}
