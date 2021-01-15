package com.coolway.service.student;

import com.coolway.entity.Student;
import com.coolway.mapper.student.StudentDO;
import com.coolway.mapper.student.StudentMapper;
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


    /**
     * 分页查询获取数据集合
     *
     * @param studentDO
     * @return
     */
    @Override
    public List list(StudentDO studentDO) {
        return studentMapper.getAllStudent();
    }
}
