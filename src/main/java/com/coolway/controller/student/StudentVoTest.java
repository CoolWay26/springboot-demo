package com.coolway.controller.student;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "test.student")
public class StudentVoTest {

    private String name;

    private Integer age;
}
