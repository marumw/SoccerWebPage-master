package com.example.spring03.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.spring03.dto.MailDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {
	
	private JavaMailSender mailSender;
    
    @Async
    public void justSend(MailDto mdto) {
    	log.info("justSend(mdto = {})", mdto);
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mdto.getEmail());
        message.setSubject(mdto.getTitle());
        message.setText(mdto.getMessage());
        log.info(message.toString());
        mailSender.send(message);
    }
}
