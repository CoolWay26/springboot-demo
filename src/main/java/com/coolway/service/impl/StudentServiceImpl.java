package com.coolway.service.impl;

import com.coolway.entity.Student;
import com.coolway.mapper.student.StudentMapper;
import com.coolway.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired(required = false)
    StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }
}
