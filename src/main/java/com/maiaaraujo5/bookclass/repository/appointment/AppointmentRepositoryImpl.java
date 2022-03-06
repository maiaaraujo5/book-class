package com.maiaaraujo5.bookclass.repository.appointment;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import com.maiaaraujo5.bookclass.repository.appointment.model.AppointmentDocument;
import com.maiaaraujo5.bookclass.repository.appointment.provider.AppointmentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final AppointmentMongoRepository appointmentMongoRepository;

    @Autowired
    public AppointmentRepositoryImpl(AppointmentMongoRepository appointmentMongoRepository) {
        this.appointmentMongoRepository = appointmentMongoRepository;
    }

    @Override
    public void save(Appointment appointment) {
        AppointmentDocument appointmentDocument = new AppointmentDocument(appointment);
        this.appointmentMongoRepository.save(appointmentDocument);

    }

    @Override
    public Optional<Appointment> findByTeacherIdAndTime(String teacherId, LocalDateTime appointmentTime) {
        Optional<AppointmentDocument> appointmentDocument = Optional.ofNullable(
                this.appointmentMongoRepository.findAppointmentDocumentByTeacherIdAndTime(teacherId, appointmentTime));

        if (appointmentDocument.isEmpty()) {
            return Optional.empty();
        }

        Appointment appointment = new Appointment(
                appointmentDocument.get().getAppointmentId(),
                appointmentDocument.get().getTeacherId(),
                appointmentDocument.get().getTime(),
                appointmentDocument.get().getCreatedAt());

        return Optional.of(appointment);
    }
}
