package com.maiaaraujo5.bookclass.repository.appointment;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.Optional;

public class AppointmentRepositoryImpl implements AppointmentRepository{

    @Override
    public void save(Appointment appointment) {

    }

    @Override
    public Optional<Appointment> findByTeacherIdAndTime(String teacherId, LocalDateTime appointmentTime) {
        return Optional.empty();
    }
}
