package com.project.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender javaMailSender;

	@Async
	public void sendSimpleMessage(String to, String subject, String text) throws SendFailedException {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();

			message.setContent(text, "text/html");
			MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
			// WassimBenBrahim@hotmail.com
			helper.setFrom("wassimpidev@gmail.com");
			helper.setTo(to);
			helper.setSubject(subject);
			// helper.setText(text);
			javaMailSender.send(message);

		} catch (MessagingException e) {
			// LOGGER.error("failed to send email", e);
			throw new IllegalStateException("failed to send email");
		}
	}
}