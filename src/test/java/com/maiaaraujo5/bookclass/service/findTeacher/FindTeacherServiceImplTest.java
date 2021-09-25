package com.maiaaraujo5.bookclass.service.findTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherNotFound;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import com.maiaaraujo5.bookclass.service.createTeacher.CreateTeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class FindTeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private FindTeacherServiceImpl findTeacherService;

    @Test
    void should_find_teacher_successfully() {
        Teacher teacher = new Teacher("123", "John", "Doe", "johndoe@johndoe.com");
        when(teacherRepository.FindByEmail(anyString())).thenReturn(Optional.of(teacher));

        Teacher teacher1 = findTeacherService.execute(anyString());

        verify(teacherRepository, times(1)).FindByEmail(anyString());

        assertThat(teacher1, notNullValue());
    }

    @Test
    void should_throws_exception_when_teacher_is_not_found() {

        TeacherNotFound thrown = assertThrows(
                TeacherNotFound.class,
                () -> {
                    when(teacherRepository.FindByEmail(anyString())).thenReturn(Optional.empty());
                    Teacher teacher = findTeacherService.execute(anyString());
                }
        );

        assertThat(thrown.getMessage(), is(equalTo("Teacher not found")));
    }
}