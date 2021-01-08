package com.coolway;

import com.coolway.controller.common.quartzjob.DemoScheduledExecutorService;
import com.coolway.controller.common.quartzjob.DemoTimerTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 官方示例工程中的测试代码
 */
@SpringBootApplication
//@EnableScheduling
@EnableAsync
public class SampleController {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
