package com.travel.travtronics.model;

public class UserExtraModel {

	private String fullName;
	private String orgName;
	private String empName;
	private String menu;

	public UserExtraModel() {
		super();
	}

	public UserExtraModel(String fullName, String orgName, String empName, String menu) {
		//super();
		this.fullName = fullName;
		this.orgName = orgName;
		this.empName = empName;
		this.menu = menu;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	

}
