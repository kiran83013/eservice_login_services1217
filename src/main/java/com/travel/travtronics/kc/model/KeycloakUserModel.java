package com.travel.travtronics.kc.model;

import java.util.List;



public class KeycloakUserModel {

	private String firstName;

	private String lastName;

	private String email;

	private Boolean enabled;

	private String username;

	private List<CreadentialsModel> credentials;

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

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CreadentialsModel> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<CreadentialsModel> credentials) {
		this.credentials = credentials;
	}

}
