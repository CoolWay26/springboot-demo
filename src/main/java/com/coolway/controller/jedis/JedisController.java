package com.coolway.controller.jedis;

import com.coolway.service.thread.JedisTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/jedis")
@Api(tags = "Jedis测试")
public class JedisController {
    @Autowired
    private JedisTestService jedisTestService;

    @PostMapping("/helloWorld")
    @ApiOperation(value = "helloWorld测试")
    public void helloWorld() {
        //连接redis
        Jedis jedis = new Jedis("localhost", 6379);
        //如果redis服务设置了密码则需要下面这行
        //jedis.auth("123456");
        System.out.println("success!");
        jedis.set("name", "hello world!");
        System.out.println("redis中存的name为" + jedis.get("name"));
    }

    @PostMapping("/countTest")
    @ApiOperation(value = "计数测试")
    public void countTest(String key) throws InterruptedException {
        while (true) {
            jedisTestService.countTest(key);
            Thread.sleep(1000);
        }
    }
}
