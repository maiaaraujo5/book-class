package com.maiaaraujo5.bookclass.service.createAppointment;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import com.maiaaraujo5.bookclass.exception.AppointmentAlreadyExists;
import com.maiaaraujo5.bookclass.repository.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class CreateAppointmentServiceImpl implements CreateAppointmentService {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public CreateAppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment execute(Appointment appointment) {
        Optional<Appointment> found = this.appointmentRepository
                .findByTeacherIdAndTime(appointment.getTeacherId(), appointment.getTime());

        if (found.isPresent()) {
            throw new AppointmentAlreadyExists("One appointment with this teacher in that hour already exists");
        }

        appointment.setId(UUID.randomUUID().toString());
        appointment.setCreatedAt(LocalDateTime.now());

        this.appointmentRepository.save(appointment);

        return appointment;
    }
}
