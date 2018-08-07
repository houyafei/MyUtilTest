package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq写操作
 *
 * @author yafei.hou  on 2018/7/12
 */
public class RabbitMqWrite {


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("rabbitmq");
        factory.setPassword("123456");
        factory.setHost("114.141.173.17");
        factory.setPort(8917);
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        final Channel channel = conn.createChannel();
        //声明交换器
        String exchangeName = "balance-route-dev";
        channel.exchangeDeclare(exchangeName, "direct", true);
        //声明队列
        String queueName = channel.queueDeclare().getQueue();
        String routingKey = "balance_dev_measure_raw_queue";
        //绑定队列，
        channel.queueBind(queueName, exchangeName, routingKey);
        //发布消息
        byte[] messageBodyBytes = "quit".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
        channel.close();
        conn.close();

    }

}
