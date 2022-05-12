package br.com.couto.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.couto.dtos.EmailDto;
import br.com.couto.models.Email;
import br.com.couto.services.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/send")
	public ResponseEntity<Object> sendEmail(@RequestBody @Valid EmailDto emailDto) {
		
		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		return ResponseEntity.status(HttpStatus.CREATED).body(emailService.sendEmail(email));

	}
	
	@GetMapping
	public String name() {
		return "ola";
	}

}
