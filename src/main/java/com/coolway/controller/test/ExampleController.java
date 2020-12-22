package com.coolway.controller.test;


import com.coolway.controller.common.quartzjob.DemoScheduledExecutorService;
import com.coolway.controller.common.quartzjob.DemoTimerTask;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/freemakerExample")
    @ApiOperation(value = "freemaker测试", httpMethod = "GET", notes = "跳转到example.ftl")
    public String getFtlExample(Map<String, Object> map) {
        map.put("name", "zhangsan");
        return "exampleController";
    }

    @GetMapping("/timer")
    public void timer() {
        TimerTask timerTask = new DemoTimerTask();
        Timer timer = new Timer();
        //指定任务开始延迟时间和执行频率，单位毫秒
        timer.schedule(timerTask, 5000, 10000);
    }

    @GetMapping("/scheduledExecutorService")
    public void scheduledExecutorService() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new DemoScheduledExecutorService(),5,10, TimeUnit.SECONDS);
    }
}
