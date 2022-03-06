package com.maiaaraujo5.bookclass.service.createTeacher;

import com.maiaaraujo5.bookclass.domain.teacher.Schedule;
import com.maiaaraujo5.bookclass.domain.teacher.Teacher;
import com.maiaaraujo5.bookclass.domain.teacher.WorkTime;
import com.maiaaraujo5.bookclass.exception.TeacherAlreadyExists;
import com.maiaaraujo5.bookclass.repository.teacher.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private CreateTeacherServiceImpl createTeacher;

    @Test
    void should_create_teacher_successfully() {
        List<Schedule> scheduleList = Collections.singletonList(new Schedule(12, 19));
        List<WorkTime> workTimeList = Collections.singletonList(new WorkTime("0", scheduleList));

        Teacher input = new Teacher("", "", "", "", workTimeList, LocalDateTime.now());
        when(teacherRepository.FindByEmail(anyString())).thenReturn(Optional.empty());

        Teacher teacher = createTeacher.execute(input);

        verify(teacherRepository, Mockito.times(1)).Save(any());
        assertThat(teacher, notNullValue());
    }

    @Test
    void should_trow_exception_when_user_already_exist_in_repository() {
        List<Schedule> scheduleList = Collections.singletonList(new Schedule(12, 19));
        List<WorkTime> workTimeList = Collections.singletonList(new WorkTime("0", scheduleList));


        TeacherAlreadyExists thrown = assertThrows(
                TeacherAlreadyExists.class,
                () -> {
                    when(teacherRepository.FindByEmail(anyString())).thenReturn(Optional.of(new Teacher()));
                    Teacher input = new Teacher("", "", "", "", workTimeList, LocalDateTime.now());
                    createTeacher.execute(input);
                }
        );

        assertThat(thrown.getMessage(), is(equalTo("This teacher already exists")));
        verify(teacherRepository, Mockito.times(0)).Save(any());

    }
}