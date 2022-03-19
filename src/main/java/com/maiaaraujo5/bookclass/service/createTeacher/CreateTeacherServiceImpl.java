package com.maiaaraujo5.bookclass.service.createTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateTeacherServiceImpl implements CreateTeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public CreateTeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher execute(Teacher teacher) {
        Optional<Teacher> found = this.teacherRepository.FindByEmail(teacher.getEmail());
        if (found.isPresent()) {
            throw new TeacherAlreadyExists("This teacher already exists");
        }
        
        teacher.setUserId(UUID.randomUUID().toString());
        teacher.setCreatedAt(LocalDateTime.now());
        this.teacherRepository.Save(teacher);

        return teacher;
    }
}
