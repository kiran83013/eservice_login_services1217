package com.travel.travtronics.kc.model;

import java.util.List;
import java.util.Map;

public class GetUserModel {

	private String id;

	private Long createdTimestamp;

	private Boolean enabled;

	private Boolean totp;

	private Boolean emailVerified;

	private String firstName;

	private String lastName;

	private List<?> disableableCredentialTypes;
	
	private List<?> requiredActions;
	
	private Long notBefore;
	
	private Map<String,Boolean> access;

	private String username;
	
	

	public Long getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Long createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getTotp() {
		return totp;
	}

	public void setTotp(Boolean totp) {
		this.totp = totp;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
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

	public List<?> getDisableableCredentialTypes() {
		return disableableCredentialTypes;
	}

	public void setDisableableCredentialTypes(List<?> disableableCredentialTypes) {
		this.disableableCredentialTypes = disableableCredentialTypes;
	}

	public List<?> getRequiredActions() {
		return requiredActions;
	}

	public void setRequiredActions(List<?> requiredActions) {
		this.requiredActions = requiredActions;
	}

	public Long getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(Long notBefore) {
		this.notBefore = notBefore;
	}

	public Map<String, Boolean> getAccess() {
		return access;
	}

	public void setAccess(Map<String, Boolean> access) {
		this.access = access;
	}

	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
