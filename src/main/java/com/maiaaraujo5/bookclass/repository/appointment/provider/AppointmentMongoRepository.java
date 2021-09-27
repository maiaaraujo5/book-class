package com.maiaaraujo5.bookclass.repository.appointment.provider;

import com.maiaaraujo5.bookclass.repository.appointment.model.AppointmentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentMongoRepository extends MongoRepository<AppointmentDocument, String> {
    AppointmentDocument findAppointmentDocumentByTeacherIdAndTime(String teacherId, LocalDateTime appointmentTime);
}
