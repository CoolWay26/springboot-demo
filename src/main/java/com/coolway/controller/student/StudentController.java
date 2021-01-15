package com.coolway.controller.student;

import com.coolway.controller.common.ResponseResult;
import com.coolway.controller.common.page.PageParam;
import com.coolway.entity.Student;
import com.coolway.mapper.student.StudentDO;
import com.coolway.service.student.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Student")
@Api(tags = "学生功能服务")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/getAllStudent")
    @ApiOperation(value = "获取所有Student", httpMethod = "GET")
    public ResponseResult getAllStudent() {
        List<Student> studentList = studentService.getAllStudent();
        return new ResponseResult().resultFlag(true).data(studentList);
    }

    @GetMapping("/pageAllStudent/page/{page}/size/{size}")
    @ApiOperation(value = "分页查询")
    public ResponseResult pageAllStudent(PageParam<StudentDO> pageParam) {
        return new ResponseResult().resultFlag(true).data(studentService.page(pageParam));
    }
}
