package com.maiaaraujo5.bookclass.controller.shared.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Subject {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final List<String> tags;
}
