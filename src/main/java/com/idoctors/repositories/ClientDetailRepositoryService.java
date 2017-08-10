/**
 * @author Shukri Shukriev
 *
 */
package com.idoctors.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.idoctors.models.ClientSocialDetails;


public class ClientDetailRepositoryService implements UserDetailsService{
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ClientSocialDetails client = clientRepository.findByEmail(email);
		
		if(client == null){
			throw new UsernameNotFoundException("No user found wih email: " + email);
		}
		
		ClientSocialDetails clientSocialDetails = new ClientSocialDetails.Builder()
		.id(client.getId())
		.firstName(client.getFirstName())
		.lastName(client.getLastName())
		.role(client.getRole())
		.build();
											
		return clientSocialDetails;
	}

}
