package com.maiaaraujo5.bookclass.repository.teacher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDocument {
    @Field("startHour")
    int startHour;
    @Field("endHour")
    int endHour;
}
