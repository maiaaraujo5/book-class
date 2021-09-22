package com.maiaaraujo5.bookclass.service.createTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTeacherImplTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private CreateTeacherImpl createTeacher;

    @Test
    void should_create_teacher_successfully() {
        Teacher input = new Teacher("", "", "", "");
        when(teacherRepository.FindByEmail(anyString())).thenReturn(null);

        Teacher teacher = createTeacher.Execute(input);

        verify(teacherRepository, Mockito.times(1)).Create(any());
        assertThat(teacher, notNullValue());
    }

    @Test
    void should_trow_exception_when_user_already_exist_in_repository() {
        TeacherAlreadyExists thrown = assertThrows(
                TeacherAlreadyExists.class,
                () -> {
                    when(teacherRepository.FindByEmail(anyString())).thenReturn(new Teacher());
                    Teacher input = new Teacher("", "", "", "");
                    createTeacher.Execute(input);
                }
        );

        assertThat(thrown.getMessage(), is(equalTo("This teacher already exists")));
        verify(teacherRepository, Mockito.times(0)).Create(any());

    }
}