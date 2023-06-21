package com.travel.travtronics.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "a3m_account_details")
public class BeUserDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@Column(name = "user_id")
	private Long userId; 
	
	@Column(name = "dept_id")
	private Integer deptId;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "salutation")
	private String salutation;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "dateofbirth")
	private LocalDate dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "postalcode")
	private String postalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "language")
	private String language;

	@Column(name = "timezone")
	private String timeZone;

	@Column(name = "citimezone")
	private String cityTimezone;

	@Column(name = "picture")
	private String picture;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "locationvalue")
	private String locationValue;

	@Column(name = "locationdescription")
	private String locationDescription;

	@Column(name = "costcentervalue")
	private String costCenterValue;

	@Column(name = "costcenterdescription")
	private String costCenterDescription;

	@Column(name = "companyvalue")
	private String companyValue;

	@Column(name = "companydescription")
	private String companyDescription;

	@Column(name = "shift_id")
	private String shiftId;

	@Column(name = "is_manager")
	private String isManager;

	@Column(name = "manager_account_id")
	private String managerAccountId;

	@Column(name = "ebs_user_id")
	private String ebsUserId;

	@Column(name = "ebs_emp_id")
	private String ebsEmpId;

	@Column(name = "sales_user_id")
	private String salesUserId;

	@Column(name = "social_username_1")
	private String socialUsername1;

	@Column(name = "social_username_2")
	private String socialUsername2;

	@Column(name = "social_username_3")
	private String socialUsername3;

	@Column(name = "social_username_4")
	private String socialUsername4;

	@Column(name = "social_username_5")
	private String socialUsername5;

	@CreationTimestamp
	@Column(name = "createddate", updatable = false)
	private Date createdDate;

	@Column(name = "createby", updatable = false)
	private String createBy;

	@UpdateTimestamp
	@Column(name = "updateddate")
	private Date updatedDate;

	@Column(name = "updateby")
	private String updateBy;

	@Column(name = "phonenumber", updatable = false)
	private String phoneNumber;

	@Column(name = "phonecode")
	private String phoneCode;

	@Column(name = "assignpettycashamount")
	private Double assignPettyCashAmount;

	@Column(name = "pettycashopenbalance")
	private Double pettyCashOpenBalance;

	@Column(name = "is_supervisor")
	private String isSupervisor;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "queue_email_address")
	private String queueEmailAddress;

	@Column(name = "overwrite_booking_email")
	private String overwriteBookingEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	

	
}
