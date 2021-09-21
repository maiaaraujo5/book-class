package com.maiaaraujo5.bookclass.domain.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String Id;
    private String name;
    private String lastname;
    private String email;
}
