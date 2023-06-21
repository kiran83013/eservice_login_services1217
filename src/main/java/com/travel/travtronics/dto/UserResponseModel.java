package com.travel.travtronics.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.travtronics.model.BeUserRoleModel;

public class UserResponseModel {

	private Integer userId;

	private String employeeId;

	private Integer orgId;

	private String userName;

	private String email;

	private String emailSecondary;

	private String phoneNumber;

	private String phoneSecondary;

	private String createdDate;

	private String verifiedOn;

	private String lastSignedInOn;

	private String resetSentOn;

	private String deletedOn;

	private String suspendedOn;

	private Double employeeBookingAmount;

	private Double creditLimitAmount;

	private Double runningBalance;

	private Integer userUpdatePassword;

	private String passwordValid;

	private String startDate;

	private String endDate;

	private String status;

	private String fullName;

	private String salutation;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private String gender;

	private String postalCode;

	private String country;

	private String language;

	private String timeZone;

	private String cityTimezone;

	private String picture;

	private String locationId;

	private String locationValue;

	private String locationDescription;

	private String costCenterValue;

	private String costCenterDescription;

	private String companyValue;

	private String companyDescription;

	private String shiftId;

	private String isManager;

	private String managerAccountId;

	private String ebsUserId;

	private String ebsEmpId;

	private String salesUserId;

	private String socialUsername1;

	private String socialUsername2;

	private String socialUsername3;

	private String socialUsername4;

	private String socialUsername5;

	private String phoneCode;

	private Double assignPettyCashAmount;

	private Double pettyCashOpenBalance;

	private String isSupervisor;

	private String userType;

	private String queueEmailAddress;

	private String overwriteBookingEmail;
	
	private Integer deptId;

	private List<BeUserRoleModel> role;

	@JsonIgnore
	private Long id;

	public String getPasswordValid() {
		return passwordValid;
	}

	public void setPasswordValid(String passwordValid) {
		this.passwordValid = passwordValid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailSecondary() {
		return emailSecondary;
	}

	public void setEmailSecondary(String emailSecondary) {
		this.emailSecondary = emailSecondary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneSecondary() {
		return phoneSecondary;
	}

	public void setPhoneSecondary(String phoneSecondary) {
		this.phoneSecondary = phoneSecondary;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getVerifiedOn() {
		return verifiedOn;
	}

	public void setVerifiedOn(String verifiedOn) {
		this.verifiedOn = verifiedOn;
	}

	public String getLastSignedInOn() {
		return lastSignedInOn;
	}

	public void setLastSignedInOn(String lastSignedInOn) {
		this.lastSignedInOn = lastSignedInOn;
	}

	public String getResetSentOn() {
		return resetSentOn;
	}

	public void setResetSentOn(String resetSentOn) {
		this.resetSentOn = resetSentOn;
	}

	public String getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(String deletedOn) {
		this.deletedOn = deletedOn;
	}

	public String getSuspendedOn() {
		return suspendedOn;
	}

	public void setSuspendedOn(String suspendedOn) {
		this.suspendedOn = suspendedOn;
	}

	public Double getEmployeeBookingAmount() {
		return employeeBookingAmount;
	}

	public void setEmployeeBookingAmount(Double employeeBookingAmount) {
		this.employeeBookingAmount = employeeBookingAmount;
	}

	public Double getCreditLimitAmount() {
		return creditLimitAmount;
	}

	public void setCreditLimitAmount(Double creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}

	public Double getRunningBalance() {
		return runningBalance;
	}

	public void setRunningBalance(Double runningBalance) {
		this.runningBalance = runningBalance;
	}

	public Integer getUserUpdatePassword() {
		return userUpdatePassword;
	}

	public void setUserUpdatePassword(Integer userUpdatePassword) {
		this.userUpdatePassword = userUpdatePassword;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCityTimezone() {
		return cityTimezone;
	}

	public void setCityTimezone(String cityTimezone) {
		this.cityTimezone = cityTimezone;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getCostCenterValue() {
		return costCenterValue;
	}

	public void setCostCenterValue(String costCenterValue) {
		this.costCenterValue = costCenterValue;
	}

	public String getCostCenterDescription() {
		return costCenterDescription;
	}

	public void setCostCenterDescription(String costCenterDescription) {
		this.costCenterDescription = costCenterDescription;
	}

	public String getCompanyValue() {
		return companyValue;
	}

	public void setCompanyValue(String companyValue) {
		this.companyValue = companyValue;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public String getManagerAccountId() {
		return managerAccountId;
	}

	public void setManagerAccountId(String managerAccountId) {
		this.managerAccountId = managerAccountId;
	}

	public String getEbsUserId() {
		return ebsUserId;
	}

	public void setEbsUserId(String ebsUserId) {
		this.ebsUserId = ebsUserId;
	}

	public String getEbsEmpId() {
		return ebsEmpId;
	}

	public void setEbsEmpId(String ebsEmpId) {
		this.ebsEmpId = ebsEmpId;
	}

	public String getSalesUserId() {
		return salesUserId;
	}

	public void setSalesUserId(String salesUserId) {
		this.salesUserId = salesUserId;
	}

	public String getSocialUsername1() {
		return socialUsername1;
	}

	public void setSocialUsername1(String socialUsername1) {
		this.socialUsername1 = socialUsername1;
	}

	public String getSocialUsername2() {
		return socialUsername2;
	}

	public void setSocialUsername2(String socialUsername2) {
		this.socialUsername2 = socialUsername2;
	}

	public String getSocialUsername3() {
		return socialUsername3;
	}

	public void setSocialUsername3(String socialUsername3) {
		this.socialUsername3 = socialUsername3;
	}

	public String getSocialUsername4() {
		return socialUsername4;
	}

	public void setSocialUsername4(String socialUsername4) {
		this.socialUsername4 = socialUsername4;
	}

	public String getSocialUsername5() {
		return socialUsername5;
	}

	public void setSocialUsername5(String socialUsername5) {
		this.socialUsername5 = socialUsername5;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Double getAssignPettyCashAmount() {
		return assignPettyCashAmount;
	}

	public void setAssignPettyCashAmount(Double assignPettyCashAmount) {
		this.assignPettyCashAmount = assignPettyCashAmount;
	}

	public Double getPettyCashOpenBalance() {
		return pettyCashOpenBalance;
	}

	public void setPettyCashOpenBalance(Double pettyCashOpenBalance) {
		this.pettyCashOpenBalance = pettyCashOpenBalance;
	}

	public String getIsSupervisor() {
		return isSupervisor;
	}

	public void setIsSupervisor(String isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getQueueEmailAddress() {
		return queueEmailAddress;
	}

	public void setQueueEmailAddress(String queueEmailAddress) {
		this.queueEmailAddress = queueEmailAddress;
	}

	public String getOverwriteBookingEmail() {
		return overwriteBookingEmail;
	}

	public void setOverwriteBookingEmail(String overwriteBookingEmail) {
		this.overwriteBookingEmail = overwriteBookingEmail;
	}

	public List<BeUserRoleModel> getRole() {
		return role;
	}

	public void setRole(List<BeUserRoleModel> role) {
		this.role = role;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}
