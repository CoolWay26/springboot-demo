package com.coolway.common.quartzjob;

import java.util.Date;
import java.util.TimerTask;

public class DemoTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("Timer task1" + new Date());
    }
}
