package com.maiaaraujo5.bookclass.controller.createAppointment;

import com.maiaaraujo5.bookclass.controller.createAppointment.domain.CreateAppointmentRequest;
import com.maiaaraujo5.bookclass.controller.shared.appointment.AppointmentResponse;
import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import com.maiaaraujo5.bookclass.service.createAppointment.CreateAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appointment")
public class CreateAppointmentController {

    private final CreateAppointmentService createAppointmentService;

    @Autowired
    public CreateAppointmentController(CreateAppointmentService createAppointmentService) {
        this.createAppointmentService = createAppointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        Appointment appointmentRequest = new Appointment(createAppointmentRequest.getTeacherId(),
                createAppointmentRequest.getTime());

        Appointment appointment = this.createAppointmentService.execute(appointmentRequest);

        AppointmentResponse createAppointmentResponse = new AppointmentResponse(appointment);

        return new ResponseEntity<>(createAppointmentResponse, HttpStatus.CREATED);
    }
}
