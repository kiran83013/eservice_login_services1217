package com.travel.travtronics.dto;

public class UserSearchResponse {
	private Integer userId;

	private Integer employeeId;

	private String employeeName;

	private Integer orgId;

	private String orgName;

	private String userName;

	private String email;

	private String emailSecondary;

	private String phoneNumber;

	private String phoneCode;

	private String phoneSecondary;

	private Integer buId;

	private String buName;

	private Integer locationId;

	private String locationValue;

	private Integer designationId;

	private String designationName;

	private String roleKey;

	private String roleName;

	private Integer roleId;

	private Integer departmentId;

	private String departmentName;

	private String status;

	private String createdDate;

	private String startDate;

	private Integer ccId;

	private String ccName;
	
	private String shiftFrom;

	private String shiftTo;

	private Long shiftId;
	
	private String shiftName;
	
	private String timeZone;
	
	private String timeZoneName;
	
	private Long userShiftAssignmentId; 
	
	
	public UserSearchResponse(Integer userId, Integer employeeId, String employeeName, Integer orgId, String orgName,
			String userName, String email, String emailSecondary, String phoneNumber, String phoneCode,
			String phoneSecondary, Integer buId, String buName, Integer locationId, String locationValue,
			Integer designationId, String designationName, String roleKey, String roleName, Integer roleId,
			Integer departmentId, String departmentName, String status, String createdDate, String startDate,
			Integer ccId, String ccName, String shiftFrom, String shiftTo, Long shiftId, String shiftName,
			String timeZone, String timeZoneName, Long userShiftAssignmentId) {
		super();
		this.userId = userId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.userName = userName;
		this.email = email;
		this.emailSecondary = emailSecondary;
		this.phoneNumber = phoneNumber;
		this.phoneCode = phoneCode;
		this.phoneSecondary = phoneSecondary;
		this.buId = buId;
		this.buName = buName;
		this.locationId = locationId;
		this.locationValue = locationValue;
		this.designationId = designationId;
		this.designationName = designationName;
		this.roleKey = roleKey;
		this.roleName = roleName;
		this.roleId = roleId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.status = status;
		this.createdDate = createdDate;
		this.startDate = startDate;
		this.ccId = ccId;
		this.ccName = ccName;
		this.shiftFrom = shiftFrom;
		this.shiftTo = shiftTo;
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.timeZone = timeZone;
		this.timeZoneName = timeZoneName;
		this.userShiftAssignmentId = userShiftAssignmentId;
	}

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getPhoneSecondary() {
		return phoneSecondary;
	}

	public void setPhoneSecondary(String phoneSecondary) {
		this.phoneSecondary = phoneSecondary;
	}

	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getShiftFrom() {
		return shiftFrom;
	}

	public void setShiftFrom(String shiftFrom) {
		this.shiftFrom = shiftFrom;
	}

	public String getShiftTo() {
		return shiftTo;
	}

	public void setShiftTo(String shiftTo) {
		this.shiftTo = shiftTo;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public Long getUserShiftAssignmentId() {
		return userShiftAssignmentId;
	}

	public void setUserShiftAssignmentId(Long userShiftAssignmentId) {
		this.userShiftAssignmentId = userShiftAssignmentId;
	}
	
	
}
