package com.coolway.controller.common.aspect;

import com.coolway.annotation.RequiresLogin;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * aop处理自定义注解
 */
@Aspect
@Component
public class LoginAspect {
    //    切点表达式，如果切点是统一的，可以这样抽取处理，方便通知指定切点
    //如果通知对应不同的方法，那么要给通知传入对应的切点表达式
    @Pointcut("@annotation(com.coolway.annotation.RequiresLogin)")
    private void loginAspectPointcut() {
    }

    //定义通知
    @Before("loginAspectPointcut()")
    public void beforeLogin(JoinPoint joinPoint) throws ClassNotFoundException {
        //为方便阅读，变量的值注释在后面，不在上方注释
        String targetName1 = joinPoint.getTarget().getClass().getName();    //com.coolway.controller.test.ExampleController
        //通过切点方法获取信息
        Object targetObj = joinPoint.getTarget();   //获得切点所在Controller的对象，如ExampleController
        Class targetClass = targetObj.getClass();   //获得切点所在Controller的class com.coolway.controller.test.ExampleController
        String targetName = targetClass.getName();  //com.coolway.controller.test.ExampleController

        //直接获取切点方法上的注解信息
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;

        //通过MethodSignature获取Method
        Method targetMethod = methodSignature.getMethod();
        //通过Method获取其注解信息
        String targetLoginName = targetMethod.getAnnotation(RequiresLogin.class).loginName();
        System.out.println(targetLoginName);

        //获得某个class的所有方法的数组
        Method[] methods = targetClass.getMethods();
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method method : methods) {
                //判断方法是否有该注解
                if (method.isAnnotationPresent(RequiresLogin.class)) {
                    System.out.println("此处使用RequiresLogin注解一次：" + method.getName());
                    //获取注解传入的信息，还有其他操作，此处不进行一一尝试
                    String loginName = method.getAnnotation(RequiresLogin.class).loginName();
                    System.out.println(loginName);
                }
            }
        }

        Class class1 = Class.forName(targetName);

        System.out.println("开始访问，查看是否登录");
    }

    @After("@annotation(com.coolway.annotation.RequiresLogin)")
    public void afterLogin() {
        System.out.println("结束访问");
    }
}
