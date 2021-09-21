package com.maiaaraujo5.bookclass.service.createTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTeacherImpl implements CreateTeacher {

    private final TeacherRepository teacherRepository;

    @Autowired
    public CreateTeacherImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher Execute(Teacher teacher) {
        Teacher found = this.teacherRepository.FindByEmail(teacher.getEmail());
        if (found != null) {
            throw new TeacherAlreadyExists("This teacher already exists");
        }

        teacher.setId(UUID.randomUUID().toString());
        this.teacherRepository.Create(teacher);

        return teacher;
    }
}
