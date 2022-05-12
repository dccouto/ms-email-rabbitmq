package br.com.couto.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.couto.enums.StatusEmail;
import br.com.couto.models.Email;
import br.com.couto.repositories.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Transactional
	public Email sendEmail(Email email) {
		
		SimpleMailMessage message = prepareEmail(email);
		try {
			javaMailSender.send(message);
			email.setStatus(StatusEmail.SENT);
		}catch (Exception e) {
			email.setStatus(StatusEmail.ERROR);
		}
		
		return emailRepository.save(email);
	}

	private static SimpleMailMessage prepareEmail(Email email) {
		email.setSendDate(LocalDateTime.now());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(email.getFrom());
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		return message;
	}

}
