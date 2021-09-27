package com.maiaaraujo5.bookclass.service.createAppointment;

import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import com.maiaaraujo5.bookclass.exception.AppointmentAlreadyExists;
import com.maiaaraujo5.bookclass.repository.appointment.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private CreateAppointmentServiceImpl createAppointmentService;

    @Test
    void should_create_appointment_successfully() {

        Appointment input = new Appointment("123", LocalDateTime.now());
        when(appointmentRepository.findByTeacherIdAndTime(anyString(), any())).thenReturn(Optional.empty());

        Appointment appointment = createAppointmentService.execute(input);
        verify(appointmentRepository, times(1)).save(any());
        assertThat(appointment, notNullValue());
        assertThat(appointment.getTeacherId(), is("123"));
    }

    @Test
    void should_throw_exception_when_appointment_for_that_time_already_exists() {
        Appointment appointment = new Appointment("123", LocalDateTime.now());
        AppointmentAlreadyExists thrown = assertThrows(
                AppointmentAlreadyExists.class,
                () -> {
                    when(appointmentRepository.findByTeacherIdAndTime(anyString(), any())).thenReturn(Optional.of(appointment));
                    createAppointmentService.execute(appointment);
                });
        verify(appointmentRepository, times(0)).save(any());
        assertThat(thrown.getMessage(), is("One appointment with this teacher in that hour already exists"));
    }
}