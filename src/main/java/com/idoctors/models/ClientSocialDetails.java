/**
 * @author Shukri Shukriev
 *
 */
package com.idoctors.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ClientSocialDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public ClientSocialDetails(){}
	
	public ClientSocialDetails(String id, String email, String first_name, String last_name){
		
	}

    public static Builder getBuilder() {
        return new Builder();
    }
	
    public static class Builder {
		private String id;
		private String firstName;
		private String lastName;
		private String email;
		private Role role;
		private Set<GrantedAuthority> authorities;
		
		public Builder(){
			this.authorities = new HashSet<>();
		}
		
		public Builder id(String id){
			this.id = id;
			return this;
		}
		
		public Builder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		
		public Builder email(String email){
			this.email = email;
			return this;
		}
		
		public Builder role(Role role){
			this.role = role;
			
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			this.authorities.add(authority);
			
			return this;
		}
			
		public ClientSocialDetails build() {
			ClientSocialDetails client = new ClientSocialDetails(id, email, firstName, lastName);
			client.id = id;
			client.firstName = firstName;
			client.lastName = lastName;
			client.role = role;
			
			return client;
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
