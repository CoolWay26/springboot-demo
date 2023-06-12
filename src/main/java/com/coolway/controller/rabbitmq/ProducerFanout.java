package com.coolway.controller.rabbitmq;

import com.coolway.common.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerFanout {
    private final static String QUEUE_NAME = "queues.test1";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个新的连接
        Connection connection = RabbitMQUtil.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明（创建）一个交换机
        // 第一个参数表示交换机名称
        // 第二个参数为交换机类型
        // 第三个参数为是否持久化
        channel.exchangeDeclare("amq.fanout", "fanout",true);
        String message = "Hello RabbitMQ";
        //发送消息到队列中basicPublish()
        // 第一个参数为交换机名称
        // 第二个参数为队列名称
        // 第三个参数为消息的其他属性
        // 第四个参数为发送信息的主体（字节格式）
        channel.basicPublish("amq.fanout", "", null, message.getBytes("UTF-8"));
        //释放资源（关闭通道和连接）
        channel.close();
        connection.close();
    }
}
