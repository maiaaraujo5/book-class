package com.maiaaraujo5.bookclass.controller.createTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Schedule;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import com.maiaaraujo5.bookclass.service.createTeacher.CreateTeacherService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateTeacherController.class)
class CreateTeacherServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTeacherService createTeacherService;

    @Test
    void should_successfully_create_a_teacher_and_response_status_created() throws Exception {
        List<Schedule> scheduleList = Collections.singletonList(new Schedule(12, 19));
        List<WorkTime> workTimeList = Collections.singletonList(new WorkTime("0", scheduleList));


        Teacher teacher = new Teacher("123", "John", "Doe", "johndoe@johndoe.com", workTimeList, LocalDateTime.now());
        given(createTeacherService.execute(any())).willReturn(teacher);

        mockMvc.perform(post("/v1/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"lastname\":\"Doe\",\"email\":\"john.doe@gmail.com\",\"worktime\":[{\"weekday\":\"01\",\"schedules\":[{\"startHour\":12,\"endHour\":19},{\"startHour\":16,\"endHour\":20}]}],\"subject\":{\"name\":\"Inglês\",\"tags\":[\"britânico\",\"avançado\"]}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.lastname", is("Doe")))
                .andExpect(jsonPath("$.email", is("johndoe@johndoe.com")));
    }

    @Test
    void should_return_status_conflict_when_service_throws_teacher_already_exists() throws Exception {
        given(createTeacherService.execute(any())).willThrow(new TeacherAlreadyExists("This teacher already exists"));
        mockMvc.perform(post("/v1/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"lastname\":\"Doe\",\"email\":\"john.doe@gmail.com\",\"worktime\":[{\"weekday\":\"01|monday\",\"schedules\":[{\"startHour\":9,\"endHour\":12},{\"startHour\":16,\"endHour\":20}]}],\"subject\":{\"name\":\"Inglês\",\"tags\":[\"britânico\",\"avançado\"]}}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status", is(HttpStatus.CONFLICT.name())))
                .andExpect(jsonPath("$.message", is("This teacher already exists")));
    }

}