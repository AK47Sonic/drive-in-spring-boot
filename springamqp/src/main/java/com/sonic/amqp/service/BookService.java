package com.sonic.amqp.service;

import com.sonic.amqp.bean.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * BookService
 *
 * @author Sonic
 * @since 2019/6/2
 */
@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @RabbitListener(queues = {"atguigu.news"})
    public void receive(Book book) {
        logger.info("msg: {}", book);
    }

    @RabbitListener(queues = {"atguigu"})
    public void receiveMsg(Message message) {

//        Object o = ByteObjectUtil.ByteToObject(message.getBody());

        // 如果使用String 对象的 getBytes() ，逆操作使用new String(bytes)
        logger.info("message body: {}", new String(message.getBody()));

        // 使用对象输出流转字节，才能用输入流转对象，这里不行
//        logger.info("message body: {}", ByteObjectUtil.ByteToObject(message.getBody()));
        logger.info("message properties: {}", message.getMessageProperties());
    }

}
