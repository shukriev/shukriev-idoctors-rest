package com.idoctors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoctors.domain.Client;
import com.idoctors.repositories.ClientRepository;
import com.idoctors.services.ClientService;


@Service
public class ClientServiceImpl implements ClientService {
	private ClientRepository clientRepository;
	
	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Override
	public Client registerNewClientAccount(Client client) {
		return clientRepository.save(client);
	}

}
