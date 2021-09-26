package com.maiaaraujo5.bookclass.repository.teacher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class WorkTimeDocument {
    @Field("start_at")
    int startAt;
    @Field("end_at")
    int endAt;
}
