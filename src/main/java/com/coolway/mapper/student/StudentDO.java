package com.coolway.mapper.student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class StudentDO {
    String id;

    String name;

    int age;
}
