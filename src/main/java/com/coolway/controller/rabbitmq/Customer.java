package com.coolway.controller.rabbitmq;

import com.coolway.controller.common.utils.RabbitMQUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {
    private final static String QUEUE_NAME = "queues.test1";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个新的连接
        Connection connection = RabbitMQUtil.getConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //监听消息，设置消息处理
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，告诉服务器我们需要哪个频道的消息
        //如果频道中有消息，就会执行回调函数handleDelivery(..)，使用消息
        //consumerTag：消费者标签，在channel.basicConsume(..)时指定
        //envelope：消息包内容，包括消息ID（区别于消息者标签），routingkey，exchange，消息重转标记
        //properties：消息属性
        //body：消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
            }
        };
        //监听队列
        //第一个参数，指定要监听的队列
        //第二个参数，是否自动回复，ACK机制
        //第三个参数，通过入参consumer执行回调函数handleDelivery(..)
        channel.basicConsume(QUEUE_NAME, true, consumer);

        //消费者不会关闭资源，需要一直监听队列
    }
}
