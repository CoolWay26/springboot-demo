package com.coolway.common.quartzjob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoSpringTask {

    @Scheduled(cron = "0/30 0/1 * * * ?")
    public void demo() {
        System.out.println("SpringTask:" + new Date());
    }
}
