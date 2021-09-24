package com.maiaaraujo5.bookclass.repository.teacher.model;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public TeacherDocument(Teacher teacher) {
        this.userId = teacher.getId();
        this.name = teacher.getName();
        this.lastname = teacher.getLastname();
        this.email = teacher.getEmail();
    }
}
