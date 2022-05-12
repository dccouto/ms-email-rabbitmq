package br.com.couto.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.couto.dtos.EmailDto;
import br.com.couto.models.Email;
import br.com.couto.services.EmailService;

@Component
public class EmailConsumer {

	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDto emailDto) {

		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		emailService.sendEmail(email);
		System.out.println("Email Status: " + email.getStatus());
	}
	
}
