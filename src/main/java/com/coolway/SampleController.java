package com.coolway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 官方示例工程中的测试代码
 */
@SpringBootApplication
//@EnableScheduling
@EnableAsync
@ServletComponentScan
public class SampleController {
    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(SampleController.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
