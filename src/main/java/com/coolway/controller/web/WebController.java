package com.coolway.controller.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebController {

    @PostMapping("/myWebServletTest")
    public void myWebServletTest() {
        System.out.println("这里不是servlet！");
    }
}
