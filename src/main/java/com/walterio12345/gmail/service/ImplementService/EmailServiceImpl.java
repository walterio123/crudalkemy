package com.walterio12345.gmail.service.ImplementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.walterio12345.gmail.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendEmailWelcome(String email,
								String body,
								String subject) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("Your Email");//example "walterio12345@gmail.com"
		message.setTo(email);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		System.out.println("Email enviado....");
		
	}
		
}


