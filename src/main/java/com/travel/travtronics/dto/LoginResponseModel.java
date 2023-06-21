package com.travel.travtronics.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.travel.travtronics.kc.model.TokenModel;

public class LoginResponseModel extends TokenModel {

	@JsonProperty("userType")
	private List<String> roles;

	private Integer userId;

	private String fullName;

	private String email;

	private String phoneNo;

	private Integer customerId;

	private String customer;

	private Integer orgId;

	private String organization;

	private Set<String> roleKeys;

	private Set<String> permissionKeys;

	private String empId;

	private String empName;

	@JsonRawValue
	private String menu;

	

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Set<String> getPermissionKeys() {
		return permissionKeys;
	}

	public void setPermissionKeys(Set<String> permissionKeys) {
		this.permissionKeys = permissionKeys;
	}

	public Set<String> getRoleKeys() {
		return roleKeys;
	}

	public void setRoleKeys(Set<String> roleKeys) {
		this.roleKeys = roleKeys;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
