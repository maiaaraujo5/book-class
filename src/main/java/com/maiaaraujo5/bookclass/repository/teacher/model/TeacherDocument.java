package com.maiaaraujo5.bookclass.repository.teacher.model;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Document(collection = "teachers")
public class TeacherDocument {
    @Id
    private String id;
    private String userId;
    private String name;
    private String lastname;
    private String email;

    private List<WorkTimeDocument> workTime;
    @Field("subjects")
    private List<SubjectDocument> subjectDocumentList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TeacherDocument(Teacher teacher) {
        this.id = teacher.getId();
        this.userId = teacher.getUserId();
        this.name = teacher.getName();
        this.lastname = teacher.getLastname();
        this.email = teacher.getEmail();
        this.workTime = convertWorkTimeList(teacher.getWorkTimeList());
        this.subjectDocumentList = teacher.getSubjectList().stream()
                .map(subject -> new SubjectDocument(subject.getName(), subject.getTags()))
                .collect(Collectors.toList());
        this.createdAt = teacher.getCreatedAt();
        this.updatedAt = teacher.getUpdatedAt();
    }

    private List<WorkTimeDocument> convertWorkTimeList(List<WorkTime> workTimeList) {
        return workTimeList.stream().map(WorkTimeDocument::new).collect(Collectors.toList());
    }
}
