package com.maiaaraujo5.bookclass.domain.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String Id;
    private String name;
    private String lastname;
    private String email;
    private List<WorkTime> workTimeList;
    private LocalDateTime createdAt;

    public Teacher(String name, String lastname, String email, List<WorkTime> workTimeList) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.workTimeList = workTimeList;
    }
}
