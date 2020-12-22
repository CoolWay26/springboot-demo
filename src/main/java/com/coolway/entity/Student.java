package com.coolway.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Student {

    final String id;

    String name;
    int age;
}

@Data
class FatherLombok {

    @Override
    public String toString() {
        new Object();
        return "111";
    }
}