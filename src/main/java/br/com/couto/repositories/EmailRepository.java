package br.com.couto.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.couto.models.Email;

public interface EmailRepository extends JpaRepository<Email, UUID>{

}
