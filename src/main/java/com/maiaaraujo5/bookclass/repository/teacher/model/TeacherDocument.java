package com.maiaaraujo5.bookclass.repository.teacher.model;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Document(collection = "teachers")
public class TeacherDocument {
    @Id
    private String id;
    @Field("user_id")
    private String userId;
    private String name;
    private String lastname;
    private String email;
    @Field("work_time")
    private WorkTimeDocument workTime;
    @Field("created_at")
    private LocalDateTime createdAt;

    public TeacherDocument(Teacher teacher) {
        this.userId = teacher.getId();
        this.name = teacher.getName();
        this.lastname = teacher.getLastname();
        this.email = teacher.getEmail();
        this.workTime = new WorkTimeDocument(teacher.getWorkTime().getStartAt(), teacher.getWorkTime().getEndAt());
    }
}
