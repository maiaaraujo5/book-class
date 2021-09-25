package com.maiaaraujo5.bookclass.controller.findTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherNotFound;
import com.maiaaraujo5.bookclass.service.findTeacher.FindTeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FindTeacherController.class)
class FindTeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindTeacherService findTeacherService;

    @Test
    void should_successfully_find_a_teacher_and_response_status_created() throws Exception {
        Teacher teacher = new Teacher("123", "John", "Doe", "johndoe@johndoe.com");
        given(findTeacherService.execute(anyString())).willReturn(teacher);

        mockMvc.perform(get("/v1/teacher")
                .queryParam("email", "johndoe@johndoe.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.lastname", is("Doe")))
                .andExpect(jsonPath("$.email", is("johndoe@johndoe.com")));
    }

    @Test
    void should_return_status_not_found_when_service_throws_teacher_not_found() throws Exception {
        given(findTeacherService.execute(anyString())).willThrow(new TeacherNotFound("Teacher not found"));
        mockMvc.perform(get("/v1/teacher")
                .queryParam("email", "johndoe@johndoe.com"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(HttpStatus.NOT_FOUND.name())))
                .andExpect(jsonPath("$.message", is("Teacher not found")));
    }
}