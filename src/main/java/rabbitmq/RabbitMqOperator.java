package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * rabbit数据读取类
 *
 * @author yafei.hou  on 2018/7/12
 */
public class RabbitMqOperator {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("rabbitmq");
        factory.setPassword("123456");
        factory.setHost("114.141.173.17");
        factory.setPort(8919);
        System.out.println("host  114.141.173.17 : 8919 ");
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

        while (true) {
            //消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag, false);
                    System.out.println("消费的消息体内容：");
                    dataProcessing(body);
                    System.out.println(Arrays.toString(body));
                    System.out.println(bytesToHexString(body));

                }
            });
        }

    }


    /**
     * 数组转换成十六进制字符串
     *
     * @param bArray byte[]
     * @return HexString
     */
    private static String bytesToHexString(byte[] bArray) {
        StringBuilder sb = new StringBuilder();
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * 加密或解密
     *
     * @param data 数据
     */
    private static void dataProcessing(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] ^= 0x55;
        }
    }

}
