package com.maiaaraujo5.bookclass.repository.teacher;

import com.maiaaraujo5.bookclass.domain.teacher.Schedule;
import com.maiaaraujo5.bookclass.domain.teacher.Subject;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import com.maiaaraujo5.bookclass.repository.teacher.model.TeacherDocument;
import com.maiaaraujo5.bookclass.repository.teacher.model.WorkTimeDocument;
import com.maiaaraujo5.bookclass.repository.teacher.provider.TeacherMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return convertDocumentToDomain(teacherDocument);
    }

    @Override
    public Optional<Teacher> FindById(String id) {
        Optional<TeacherDocument> teacherDocument = Optional.ofNullable(this.teacherMongoRepository.findTeacherDocumentByUserId(id));
        return this.convertDocumentToDomain(teacherDocument);
    }

    private Optional<Teacher> convertDocumentToDomain(Optional<TeacherDocument> teacherDocument) {
        if (teacherDocument.isEmpty()) {
            return Optional.empty();
        }

        Teacher teacher = new Teacher(teacherDocument.get().getId(),
                teacherDocument.get().getUserId(),
                teacherDocument.get().getName(),
                teacherDocument.get().getLastname(),
                teacherDocument.get().getEmail(),
                convertWorkTimeDomain(teacherDocument.get().getWorkTime()),
                teacherDocument.get().getSubjectDocumentList().stream()
                        .map(subject -> new Subject(subject.getName(), subject.getTags()))
                        .collect(Collectors.toList()),
                teacherDocument.get().getCreatedAt(),
                teacherDocument.get().getUpdatedAt());

        return Optional.of(teacher);
    }

    private List<WorkTime> convertWorkTimeDomain(List<WorkTimeDocument> workTimeDocumentList) {
        List<WorkTime> list = new ArrayList<>();

        workTimeDocumentList.forEach(workTimeDocument -> {
            List<Schedule> scheduleList = workTimeDocument.getScheduleDocumentList()
                    .stream()
                    .map(scheduleDocument -> new Schedule(scheduleDocument.getStartHour(), scheduleDocument.getEndHour()))
                    .collect(Collectors.toList());

            WorkTime workTime = new WorkTime(workTimeDocument.getWeekday(), scheduleList);
            list.add(workTime);
        });

        return list;
    }
}
