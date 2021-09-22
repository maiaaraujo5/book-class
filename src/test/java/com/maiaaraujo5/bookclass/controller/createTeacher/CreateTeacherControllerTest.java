package com.maiaaraujo5.bookclass.controller.createTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.service.createTeacher.CreateTeacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateTeacherController.class)
class CreateTeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTeacher createTeacher;

    @Test
    void should_successfully_create_a_teacher_and_response_status_created() throws Exception {
        Teacher teacher = new Teacher("123","John", "Doe", "johndoe@johndoe.com");
        given(createTeacher.Execute(any())).willReturn(teacher);

        mockMvc.perform(post("/v1/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"lastname\":\"Doe\",\"email\":\"johndoe@johndoe.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.lastname", is("Doe")))
                .andExpect(jsonPath("$.email", is("johndoe@johndoe.com")));
    }
}