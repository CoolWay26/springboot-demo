package com.coolway.controller.common.quartzjob;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoQuartzTask1 {
    public void soutDate() {
        System.out.println("Quartz1:" + new Date());
    }

}
