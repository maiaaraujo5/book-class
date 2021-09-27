package com.maiaaraujo5.bookclass.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Appointment {
    private String id;
    private String teacherId;
    private LocalDateTime time;
    private LocalDateTime createdAt;

    public Appointment(String teacherId, LocalDateTime time) {
        this.teacherId = teacherId;
        this.time = time;
    }
}
