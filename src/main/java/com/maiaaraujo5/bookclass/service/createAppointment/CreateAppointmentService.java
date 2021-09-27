package com.maiaaraujo5.bookclass.service.createAppointment;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;

import java.time.LocalDateTime;

public interface CreateAppointmentService {
    Appointment execute(Appointment appointment);
}
