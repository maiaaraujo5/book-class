package com.maiaaraujo5.bookclass.repository.teacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository {
     void Create(Teacher teacher);
     Teacher FindByEmail(String email);
}
