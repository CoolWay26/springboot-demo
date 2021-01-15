package com.coolway.service.student;

import com.coolway.controller.common.page.PageService;
import com.coolway.entity.Student;
import com.coolway.mapper.student.StudentDO;

import java.util.List;

public interface IStudentService extends PageService<StudentDO, StudentDO> {
    List<Student> getAllStudent();
}
