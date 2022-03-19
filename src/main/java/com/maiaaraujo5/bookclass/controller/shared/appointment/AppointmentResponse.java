package com.maiaaraujo5.bookclass.controller.shared.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentResponse {
    private String id;
    @JsonProperty("teacher_id")
    private String teacherId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime time;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime createdAt;

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.teacherId = appointment.getTeacherId();
        this.time = appointment.getTime();
        this.createdAt = appointment.getCreatedAt();
    }
}
