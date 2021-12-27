package com.walterio12345.gmail.service;



public interface EmailService {

	void sendEmailWelcome(String email,
							String body,
							String subject);
}
