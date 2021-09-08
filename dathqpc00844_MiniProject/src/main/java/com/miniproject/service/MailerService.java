package com.miniproject.service;

import javax.mail.MessagingException;

import com.miniproject.entity.MailModel;

public interface MailerService {

	void send(MailModel mail) throws MessagingException;
	
	void send(String to, String subject, String body) throws MessagingException;
	
}
