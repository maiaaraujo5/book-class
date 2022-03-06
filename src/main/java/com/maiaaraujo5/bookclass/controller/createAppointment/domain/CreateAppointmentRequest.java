package com.maiaaraujo5.bookclass.controller.createAppointment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateAppointmentRequest {
    @JsonProperty("teacher_id")
    private String teacherId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm", timezone = "America/Sao_Paulo")
    @JsonProperty
    private LocalDateTime time;
}
