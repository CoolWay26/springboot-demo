package com.coolway.controller.common.quartzjob;

import java.util.Date;

public class DemoScheduledExecutorService implements Runnable {

    @Override
    public void run() {
        System.out.println("ScheduledExecutorService task1" + new Date());
    }
}
