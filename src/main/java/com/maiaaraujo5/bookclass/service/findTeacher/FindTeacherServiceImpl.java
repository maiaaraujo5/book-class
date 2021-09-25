package com.maiaaraujo5.bookclass.service.findTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherNotFound;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindTeacherServiceImpl implements FindTeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public FindTeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public Teacher execute(String email) {
        Optional<Teacher> teacher = this.teacherRepository.FindByEmail(email);
        if (teacher.isEmpty()) {
            throw new TeacherNotFound("Teacher not found");
        }

        return teacher.get();
    }
}
