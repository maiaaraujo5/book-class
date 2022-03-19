package com.maiaaraujo5.bookclass.domain.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String id;
    private String userId;
    private String name;
    private String lastname;
    private String email;
    private List<WorkTime> workTimeList;
    private List<Subject> subjectList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Teacher(String name, String lastname, String email, List<WorkTime> workTimeList, List<Subject> subjectList) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.workTimeList = workTimeList;
        this.subjectList = subjectList;
    }

    public Teacher(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void setLastname(String lastname) {
        if (lastname != null) {
            this.lastname = lastname;
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    public void setWorkTimeList(List<WorkTime> workTimeList) {
        if (workTimeList != null) {
            this.workTimeList = workTimeList;
        }
    }

    public void setSubjectList(List<Subject> subjectList) {
        if (subjectList != null) {
            this.subjectList = subjectList;
        }
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
