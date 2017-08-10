package com.idoctors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idoctors.domain.Client;
import com.idoctors.models.ClientSocialDetails;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	public ClientSocialDetails findByEmail(String email);
}
