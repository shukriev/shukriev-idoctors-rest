/**
 * @author Shukri Shukriev
 *
 */
package com.idoctors.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.idoctors.models.Role;

@Entity
@Table(name = "clients")
public class Client {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(name = "first_name", length = 100)
	private String firstName;
	
	@Column(name = "last_name", length = 100)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 30, nullable = false)
	private Role role;
	
	@Column(name = "profile_picture", columnDefinition="LONGBLOB")
	private byte[] profile_picture;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "client_have_authorities",
        	joinColumns = @JoinColumn(name = "clientId"),
        	inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private List<Authority> authorities = new ArrayList<>();
	
	public Client(){
		
	}

	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public byte[] getProfilePicture() {
		return profile_picture;
	}

	/**
	 * @param profile_picture the profile_picture to set
	 */
	public void setProfilePicture(byte[] profile_picture) {
		this.profile_picture = profile_picture;
	}

	public Role getRole() {
		return role;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
			    + ", role=" + role + "]";
	}
	
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		private Client client;
		
		public Builder() {
			client = new Client();
			client.role = Role.ROLE_USER;
		}
		
		public Builder id(String id) {
			client.id = id;
			return this;
		}
		
		public Builder email(String email) {
			client.email = email;
			return this;
		}
		
		public Builder firstName(String firstName) {
			client.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			client.lastName = lastName;
			return this;
		}
		
		public Builder profilePicture(byte[] profile_picture){
			client.profile_picture = profile_picture;
			return this;
		}
		
		public Builder role(Role role) {
			client.role = role;
			return this;
		}
		
		public Client build() {
			return client;
		}
	}
}
