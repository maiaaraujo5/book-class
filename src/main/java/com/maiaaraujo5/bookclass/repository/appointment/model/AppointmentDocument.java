package com.maiaaraujo5.bookclass.repository.appointment.model;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "appointments")
public class AppointmentDocument {
    @Id
    private String id;
    @Field("appointment_id")
    private String appointmentId;
    @Field("teacher_id")
    private String teacherId;
    private LocalDateTime time;
    @Field("created_at")
    private LocalDateTime createdAt;

    public AppointmentDocument(Appointment appointment) {
        this.appointmentId = appointment.getId();
        this.teacherId = appointment.getTeacherId();
        this.time = appointment.getTime();
        this.createdAt = appointment.getCreatedAt();
    }
}
