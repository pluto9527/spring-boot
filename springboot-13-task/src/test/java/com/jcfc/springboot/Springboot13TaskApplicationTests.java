package com.jcfc.springboot;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot13TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	public void send() {
		SimpleMailMessage message = new SimpleMailMessage();
		//邮件设置
		message.setSubject("邮件主题");
		message.setText("邮件内容");
		//发送的邮箱地址
		message.setTo("1234567@163.com");
		//来自
		message.setFrom("1234567@qq.com");

		mailSender.send(message);
	}

	@Test
	public void send2() throws MessagingException {
		//发送一个复杂的消息邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//第二个参数代表是否上传附件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		//邮件设置
		helper.setSubject("邮件主题");
		//第二个参数代表邮件内容是html
		helper.setText("<b style:'color:red'>邮件内容</b>", true);
		//发送的邮箱地址
		helper.setTo("1234567@163.com");
		//来自
		helper.setFrom("1234567@qq.com");

		//上传文件
		helper.addAttachment("1.jpg", new File(""));

		mailSender.send(mimeMessage);
	}


	@Test
	public void contextLoads() {
	}

}
