package com.coolway.controller.test;

import com.coolway.mapper.student.StudentDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

@Api(tags = "反射测试")
@RestController
@RequestMapping("/reflect")
public class ReflectController {

    @ApiOperation(value = "测试获取构造方法")
    @GetMapping("/getConstructor")
    public void getConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, IOException {
        //1.获取Class
        Class studentClass = Class.forName("com.coolway.mapper.student.StudentDO");

        //2.获取Constructor
        //获取所有公有构造
        Constructor[] constructors = studentClass.getConstructors();
        //获取所有构造，包括公有，默认，受保护，私有
        Constructor[] declaredConstructors = studentClass.getDeclaredConstructors();
        //获取某个公有构造，此处以无参为例
        Constructor constructor = studentClass.getConstructor();
        //获取某个非公有构造，此处以单个参数String为例
        Constructor declaredConstructor = studentClass.getDeclaredConstructor(String.class);

        //调用构造方法
        StudentDO studentDO = (StudentDO) constructor.newInstance();

        //3.获取成员变量
        //获取公有字段
        Field[] fields = studentClass.getFields();
        //获取所有字段
        Field[] declaredFields = studentClass.getDeclaredFields();
        //获取指定公有字段
        Field name = studentClass.getField("name");
        //获取指定非公有字段
        Field age = studentClass.getDeclaredField("age");

        //调用字段
        name.set(studentDO, "zhangsan");
        //暴力反射，解除私有限定
        age.setAccessible(true);
        age.set(studentDO, 20);
        System.out.println(studentDO.getName());

        //4.获取成员方法
        //获取公有方法，包括继承来的，包括Object类的
        Method[] methods = studentClass.getMethods();
        //获取所有成员方法，包括私有的，不包括继承的
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        //获取公有指定方法，参数：1.方法名 2.方法形参类型
        Method publicMethod = studentClass.getMethod("methodName", String.class);
        publicMethod.invoke(studentDO, "zhangsan");
        //获取私有指定方法
        Method privateMethod = studentClass.getDeclaredMethod("methodName", String.class);
        privateMethod.setAccessible(true);
        privateMethod.invoke(studentDO, "lisi");

        //5.读取配置文件
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("jdbc.properties");
        properties.load(fileReader);
        fileReader.close();
        String valueObj = (String)properties.get("keyName");
    }

}
