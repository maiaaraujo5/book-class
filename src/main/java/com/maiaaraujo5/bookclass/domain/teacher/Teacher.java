package com.maiaaraujo5.bookclass.domain.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String Id;
    private String name;
    private String lastname;
    private String email;
    private WorkTime workTime;
    private LocalDateTime createdAt;

    public Teacher(String name, String lastname, String email, WorkTime workTime) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.workTime = workTime;
    }
}
