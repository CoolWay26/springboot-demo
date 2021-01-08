package com.coolway.mapper.student;

import com.coolway.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getAllStudent();
}
