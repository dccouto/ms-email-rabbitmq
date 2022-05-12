package br.com.couto.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDto {
	
	@NotBlank
	private String ownerRef;
	
	@Email
	@NotBlank(message = "Campo FROM é obrigatório")
	private String from;
	
	@Email
	@NotBlank
	private String to;
	
	@NotBlank
	private String subject;
	
	@NotBlank
	private String text;
	
}
