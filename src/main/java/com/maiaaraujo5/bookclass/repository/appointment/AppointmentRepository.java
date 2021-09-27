package com.maiaaraujo5.bookclass.repository.appointment;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository {
    void save(Appointment appointment);
    Optional<Appointment> findByTeacherIdAndTime(String teacherId, LocalDateTime appointmentTime);
}
