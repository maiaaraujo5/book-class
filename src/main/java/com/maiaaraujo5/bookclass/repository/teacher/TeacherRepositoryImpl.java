package com.maiaaraujo5.bookclass.repository.teacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import com.maiaaraujo5.bookclass.repository.teacher.model.TeacherDocument;
import com.maiaaraujo5.bookclass.repository.teacher.model.WorkTimeDocument;
import com.maiaaraujo5.bookclass.repository.teacher.provider.TeacherMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeacherRepositoryImpl implements TeacherRepository {

    TeacherMongoRepository teacherMongoRepository;

    @Autowired
    public TeacherRepositoryImpl(TeacherMongoRepository teacherMongoRepository) {
        this.teacherMongoRepository = teacherMongoRepository;
    }

    @Override
    public void Save(Teacher teacher) {
        TeacherDocument teacherDocument = new TeacherDocument(teacher);
        this.teacherMongoRepository.save(teacherDocument);
    }

    @Override
    public Optional<Teacher> FindByEmail(String email) {
        Optional<TeacherDocument> teacherDocument = Optional.ofNullable(this.teacherMongoRepository.findTeacherByEmail(email));

        if (teacherDocument.isEmpty()) {
            return Optional.empty();
        }

        WorkTime workTime = new WorkTime(teacherDocument.get().getWorkTime().getStartAt(),
                teacherDocument.get().getWorkTime().getEndAt());

        Teacher teacher = new Teacher(teacherDocument.get().getUserId(),
                teacherDocument.get().getName(),
                teacherDocument.get().getLastname(),
                teacherDocument.get().getEmail(),
                workTime,
                teacherDocument.get().getCreatedAt());

        return Optional.of(teacher);
    }
}
