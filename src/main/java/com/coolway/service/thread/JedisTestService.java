package com.coolway.service.thread;

import com.coolway.common.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisDataException;

@Service
@Scope("prototype")
public class JedisTestService {
    @Autowired
    private RedisUtil redisUtil;

    @Async("taskExecutor")
    public void countTest(String id) {
        if (StringUtils.isNotEmpty(id)) {
            String count = redisUtil.get(id, 0);
            try {
                if (StringUtils.isNotEmpty(count)) {
                    //存在，自增
                    redisUtil.incr(id);
                } else {
                    //不存在，新建
                    redisUtil.set(id, String.valueOf(Long.MAX_VALUE - 10), 0);
                }
            } catch (JedisDataException e) {
                System.out.println("次数已用完！请充值！");
            } finally {
                System.out.println(Thread.currentThread().getName() + id + ":" + redisUtil.get(id, 0));
            }
        }
    }
}
