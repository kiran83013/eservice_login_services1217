package com.travel.travtronics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetToken {
	
	@JsonProperty("realm1")
	private String grant_type;
	
	
	@JsonProperty("realm2")
	private String client_id;
	
	
	@JsonProperty("realm3")
	private String user_name;
	

	@JsonProperty("realm4")
	private String password;
	
	
	@JsonProperty("realm5")
	private String client_secret;


	public String getGrant_type() {
		return grant_type;
	}


	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}


	public String getClient_id() {
		return client_id;
	}


	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getClient_secret() {
		return client_secret;
	}


	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}


	

}
