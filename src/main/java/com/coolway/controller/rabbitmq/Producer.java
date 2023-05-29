package com.coolway.controller.rabbitmq;

import com.coolway.controller.common.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生成者
 */
public class Producer {
    private final static String QUEUE_NAME = "queues.test1";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个新的连接
        Connection connection = RabbitMQUtil.getConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明（即创建）一个队列queueDeclare()
        // 第一个参数表示队列名称
        // 第二个参数为是否持久化队列（true表示是，队列将在服务器重启时生存）
        // 第三个参数为是否是独占队列（connection是否只有一个channel）
        // 第四个参数为是否自动删除队列（当所有消费者customer客户端连接断开时）
        // 第五个参数为队列的其他参数
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 10; i++) {
            String message = "Hello RabbitMQ";
            //发送消息到队列中basicPublish()
            // 第一个参数为交换机名称
            // 第二个参数为队列名称
            // 第三个参数为消息的其他属性
            // 第四个参数为发送信息的主体（字节格式）
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            //释放资源（关闭通道和连接）
        }
        channel.close();
        connection.close();
    }
}
