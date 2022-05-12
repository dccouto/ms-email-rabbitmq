package br.com.couto.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.couto.enums.StatusEmail;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_EMAIL")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PK_EMAIL")
	private UUID id;
	
	@Column(name = "CUSUA_ENVIO_EMAIL")
	private String ownerRef;
	
	@Column(name="DE")
	private String from;
	
	@Column(name="PARA")
	private String to;
	
	@Column(name="TITULO")
	private String subject;
	
	@Column(name="TEXTO", columnDefinition = "TEXT")
	private String text;
	
	@Column(name="DT_ENVIO")
	private LocalDateTime sendDate;
	
	@Column(name="STATUS")
	private StatusEmail status;
	

}
