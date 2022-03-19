package com.maiaaraujo5.bookclass.service.updateTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherNotFound;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpdateTeacherServiceImpl implements UpdateTeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public UpdateTeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public Teacher execute(Teacher teacher, String userId) {
        Optional<Teacher> teacherSaved = this.teacherRepository.FindById(userId);

        if (teacherSaved.isEmpty()) {
            throw new TeacherNotFound("Teacher not found");
        }

        teacherSaved.get().setName(teacher.getName());
        teacherSaved.get().setLastname(teacher.getLastname());
        teacherSaved.get().setEmail(teacher.getEmail());
        teacherSaved.get().setSubjectList(teacher.getSubjectList());
        teacherSaved.get().setWorkTimeList(teacher.getWorkTimeList());
        teacherSaved.get().setUpdatedAt(LocalDateTime.now());

        this.teacherRepository.Save(teacherSaved.get());

        return teacherSaved.get();
    }
}
