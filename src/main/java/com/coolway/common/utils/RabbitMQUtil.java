package com.coolway.common.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {
    private final static String host = "192.168.31.95";
    private final static int port = 5672;
    private final static String username = "admin";
    private final static String password = "123456";
    private final static String virtualHost = "hostTest";

    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);

        //创建一个新的连接
        return factory.newConnection();
    }
}
