package com.maiaaraujo5.bookclass.controller.createAppointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maiaaraujo5.bookclass.domain.appointment.Appointment;
import com.maiaaraujo5.bookclass.exception.AppointmentAlreadyExists;
import com.maiaaraujo5.bookclass.service.createAppointment.CreateAppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateAppointmentController.class)
class CreateAppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateAppointmentService createAppointmentService;

    @Test
    void should_successfully_create_an_appointment_and_response_status_created() throws Exception {
        Appointment appointment = new Appointment(
                "1",
                "123",
                LocalDateTime.of(2021, Month.JANUARY, 1, 1, 0),
                LocalDateTime.of(2020, Month.DECEMBER, 1, 1, 1)
        );

        given(createAppointmentService.execute(any())).willReturn(appointment);

        mockMvc.perform(post("/v1/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Appointment("123", LocalDateTime.of(2021, Month.JANUARY, 1, 1, 1)))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.teacher_id", is("123")))
                .andExpect(jsonPath("$.time", is("2021-01-01@01:00")))
                .andExpect(jsonPath("$.created_at", is("2020-12-01@01:01:00")))
        ;
    }

    @Test
    void should_return_status_conflict_when_service_throws_appointment_already_exists() throws Exception {
        given(createAppointmentService.execute(any())).willThrow(new AppointmentAlreadyExists("Appointment already exists"));
        mockMvc.perform(post("/v1/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Appointment("123", LocalDateTime.of(2021, Month.JANUARY, 1, 1, 1)))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(HttpStatus.CONFLICT.name())))
                .andExpect(jsonPath("$.message", is("Appointment already exists")));

    }

}