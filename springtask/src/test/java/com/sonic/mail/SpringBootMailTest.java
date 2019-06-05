package com.sonic.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * SpringBootMailTest
 *
 * @author Sonic
 * @since 2019/6/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMailTest {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void testMailSender(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("subject");
        simpleMailMessage.setText("text");
        simpleMailMessage.setTo("...");
        simpleMailMessage.setFrom("..");
        javaMailSender.send(simpleMailMessage);
    }


    @Test
    public void testComplexMailSender() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setText("html", true);
        helper.addAttachment("1.jpg", new File(""));
        helper.addAttachment("2.jpg", new File(""));
        javaMailSender.send(mimeMessage);
    }

}
