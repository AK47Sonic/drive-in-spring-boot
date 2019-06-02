package com.sonic.amqp;

import com.sonic.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * AMQPTest
 *
 * @author Sonic
 * @since 2019/6/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AMQPAppBootstrap.class})
public class AMQPTest {

    Logger logger = LoggerFactory.getLogger(AMQPTest.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//        logger.info("Create exchange successfully");

//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        //创建 binding
//        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqpadmin.haha", null));

    }

    /**
     * 单播（点对点）
     */
    @Test
    public void testPointToPointSender() {

//        rabbitTemplate.send(exchange, routingKey, message);

        // object默认当成消息体，只需要传入要发送的对象， 自动序列化，发送给rabbitmq
//        rabbitTemplate.convertAndSend(, , );

//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "The first msg");
//        map.put("data", Arrays.asList("hello", 123, true));
//        // 对象被默认序列化后发送
//        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);

//        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("西游记","吴承恩"));

        rabbitTemplate.convertAndSend("exchange.direct", "atguigu", new Book("西游记", "吴承恩"));

    }

    @Test
    public void testPointToPointReceiver() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        logger.info("object class: {}", o.getClass());
        logger.info("object: {}", o);
    }

    /**
     * 广播
     */
    @Test
    public void testTopicSender() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三国演义", "罗贯中"));
    }

}
