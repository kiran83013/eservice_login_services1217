package com.travel.travtronics.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.travel.roles")
public class KeyCloakRolesModel {


	private String adminRoleId;
	
	private String feUserRoleId;
	
	private String beUserRoleId;
	
	

	public String getFeUserRoleId() {
		return feUserRoleId;
	}

	public void setFeUserRoleId(String feUserRoleId) {
		this.feUserRoleId = feUserRoleId;
	}

	public String getBeUserRoleId() {
		return beUserRoleId;
	}

	public void setBeUserRoleId(String beUserRoleId) {
		this.beUserRoleId = beUserRoleId;
	}

	public String getAdminRoleId() {
		return adminRoleId;
	}

	public void setAdminRoleId(String adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	
	

}
