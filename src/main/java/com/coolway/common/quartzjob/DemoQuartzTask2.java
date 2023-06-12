package com.coolway.common.quartzjob;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@Data
public class DemoQuartzTask2 extends QuartzJobBean {
    /**
     * 这种方式定义任务类可以传参
     */
    String name;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(name + "DemoQuartzTask：" + new Date());
    }
}
