package br.com.couto.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.couto.dtos.EmailDto;
import br.com.couto.exceptions.EmailException;
import br.com.couto.models.Email;
import br.com.couto.services.EmailService;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

@Component
public class EmailConsumer {

	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listenRabbitMq(@Payload EmailDto emailDto) {

		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		emailService.sendEmail(email);
		System.out.println("Email Status: " + email.getStatus());
	}
	
	@SqsListener(value="${cloud.aws.sqs.address.queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void listenAwsSqs(String message) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Email email = new Email();
		try {
			//convertendo String para Objeto
			email = mapper.readValue(message, Email.class);
		} catch (JsonProcessingException e) {
			throw new EmailException(e.getMessage());
		}
		
		emailService.sendEmail(email);
		System.out.println("Email Status: " + email.getStatus());
	}
	

}
