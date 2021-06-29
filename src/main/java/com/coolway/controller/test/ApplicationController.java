package com.coolway.controller.test;

import com.coolway.controller.student.StudentVO;
import com.coolway.service.student.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/application")
@Api(tags = "测试application.yml数据配置获取")
public class ApplicationController {
    @Autowired
    private IStudentService studentService;

    @Value("${test.testname1}")
    private String testname1;

    @Value("${test.testname2}")
    private String testname2;

    @Value("${test.testname3}")
    private String testname3;

//    @Value("${test.Person}")
//    private StudentVO studentVO;
//
//    @Value("${test.PersonList}")
//    private List<StudentVO> studentVOList;

    @ApiOperation(value = "测试application.yml数据配置获取")
    @PostMapping("/testApp")
    public void testApp() {
        System.out.println(testname1);
        System.out.println(testname2);
        System.out.println(testname3);
//        System.out.println(studentVO);
//        System.out.println(studentVOList);

    }
}
