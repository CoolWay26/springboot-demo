package com.coolway.controller.config;

import com.coolway.controller.common.quartzjob.DemoQuartzTask2;
import org.quartz.JobDataMap;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@ConditionalOnExpression(value = "false")//控制quartz定时任务的开关
public class QuartzDemoConfig {
    /**
     * MethodInvokingJobDetailFactoryBean
     * 指定任务类的bean和bean中的任务方法，但不能传参，适合应用于bean方式定义的任务类
     *
     * @return MethodInvokingJobDetailFactoryBean
     */
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("demoQuartzTask1");
        bean.setTargetMethod("soutDate");
        return bean;
    }

    /**
     * JobDetailFactoryBean
     * 指定任务类，任务类必须是Job接口的实现类（QuartzJobBean就实现了Job接口）
     * 可以传参，通过JobDataMap
     *
     * @return JobDetailFactoryBean
     */
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DemoQuartzTask2.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "这是我的名字");
        bean.setJobDataMap(jobDataMap);
        return bean;
    }

    /**
     * CronTriggerFactoryBean
     */
    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/10 * * * * ?");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }

    /**
     * SchedulerFactoryBean
     * setTriggers(Trigger... triggers)可变参数，可以给调度器传多个触发器，触发多个任务，调度器内部是线程池调用
     *
     * @return
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronTrigger().getObject());
        return bean;
    }
}
