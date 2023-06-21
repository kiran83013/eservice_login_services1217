package com.travel.travtronics.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.travel.travtronics.kc.model.KeycloakUserModel;
import com.travel.travtronics.model.BePermissionModel;
import com.travel.travtronics.model.BeUserDetailsModel;
import com.travel.travtronics.model.BeUserModel;
import com.travel.travtronics.model.BeUserRoleModel;
import com.travel.travtronics.response.BeUserModelResponse;

public class ApiConverter {

	public static BeUserModel convertDtoToModel(UserDto dto) {

		BeUserModel model = new BeUserModel();

		model.setEmail(dto.getEmail().toLowerCase());
		model.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : "");

		model.setUserName(dto.getUserName());
		model.setEmployeeId(dto.getEmployeeId());
		model.setOrgId(dto.getOrgId() != null ? dto.getOrgId() : 0);
		model.setCreatedDate(LocalDateTime.now().toString());
		model.setEmailSecondary(dto.getEmailSecondary());
		model.setPhoneSecondary(dto.getPhoneSecondary());
		model.setStartDate(dto.getStartDate());
		model.setEndDate(dto.getEndDate());
		model.setVerifiedOn(dto.getVerifiedOn());
		model.setLastSignedInOn(dto.getLastSignedInOn());
		model.setResetSentOn(dto.getResetSentOn());
		model.setDeletedOn(dto.getDeletedOn());
		model.setSuspendedOn(dto.getSuspendedOn());
		model.setEmployeeBookingAmount(dto.getEmployeeBookingAmount());
		model.setCreditLimitAmount(dto.getCreditLimitAmount());
		model.setRunningBalance(dto.getRunningBalance());
		model.setUserUpdatePassword(dto.getUserUpdatePassword());
		model.setStatus(dto.getStatus() != null ? dto.getStatus() : "active");
		return model;

	}

	public static BeUserDetailsModel convertDtoToUserDeatilsModel(UserDto request, Integer userId) {
		BeUserDetailsModel userAcc = new BeUserDetailsModel();
		userAcc.setFullName(
				request.getFullName() != null && !request.getFullName().isBlank() ? request.getFullName() : "");
		userAcc.setSalutation(
				request.getSalutation() != null && !request.getSalutation().isBlank() ? request.getSalutation() : "");
		userAcc.setFirstName(
				request.getFirstName() != null && !request.getFirstName().isBlank() ? request.getFirstName() : "");
		userAcc.setLastName(
				request.getLastName() != null && !request.getLastName().isBlank() ? request.getLastName() : "");

		userAcc.setDeptId(request.getDeptId() != null ? request.getDeptId() : null);

		userAcc.setDateOfBirth(request.getDateOfBirth() != null ? request.getDateOfBirth() : null);
		userAcc.setGender(request.getGender() != null && !request.getGender().isBlank() ? request.getGender() : null);
		userAcc.setPostalCode(
				request.getPostalCode() != null && !request.getPostalCode().isBlank() ? request.getPostalCode() : null);
		userAcc.setCountry(
				request.getCountry() != null && !request.getCountry().isBlank() ? request.getCountry() : null);
		userAcc.setLanguage(
				request.getLanguage() != null && !request.getLanguage().isBlank() ? request.getLanguage() : null);
		userAcc.setTimeZone(
				request.getTimeZone() != null && !request.getTimeZone().isBlank() ? request.getTimeZone() : null);
		userAcc.setPicture(
				request.getPicture() != null && !request.getPicture().isBlank() ? request.getPicture() : null);
		userAcc.setLocationId(
				request.getLocationId() != null && !request.getLocationId().isBlank() ? request.getLocationId() : "0");
		userAcc.setLocationValue(
				request.getLocationValue() != null && !request.getLocationValue().isBlank() ? request.getLocationValue()
						: "");
		userAcc.setCostCenterValue(
				request.getCompanyValue() != null && !request.getCompanyValue().isBlank() ? request.getCompanyValue()
						: "");
		userAcc.setCostCenterDescription(
				request.getCostCenterDescription() != null && !request.getCostCenterValue().isBlank()
						? request.getCostCenterValue()
						: "");
		userAcc.setCompanyDescription(
				request.getCompanyDescription() != null && !request.getCompanyDescription().isBlank()
						? request.getCompanyDescription()
						: "");
		userAcc.setCompanyValue(
				request.getCompanyValue() != null && !request.getCompanyValue().isBlank() ? request.getCompanyValue()
						: "");
		userAcc.setShiftId(request.getShiftId() != null && !request.getShiftId().isBlank() ? request.getShiftId() : "");
		userAcc.setIsManager(
				request.getIsManager() != null && !request.getIsManager().isBlank() ? request.getIsManager() : "0");
		userAcc.setManagerAccountId(request.getManagerAccountId() != null && !request.getManagerAccountId().isBlank()
				? request.getManagerAccountId()
				: null);
		userAcc.setEbsEmpId(
				request.getEbsEmpId() != null && !request.getEbsEmpId().isBlank() ? request.getEbsEmpId() : null);
		userAcc.setEbsUserId(
				request.getEbsUserId() != null && !request.getEbsEmpId().isBlank() ? request.getEbsEmpId() : null);
		userAcc.setCreateBy(request.getCreatedBy() != null ? request.getCreatedBy().toString() : null);
		userAcc.setSalesUserId(
				request.getSalesUserId() != null && !request.getSalesUserId().isBlank() ? request.getSalesUserId()
						: null);
		userAcc.setSocialUsername1(request.getSocialUsername1() != null && !request.getSocialUsername1().isBlank()
				? request.getSocialUsername1()
				: null);
		userAcc.setSocialUsername2(request.getSocialUsername2() != null && !request.getSocialUsername2().isBlank()
				? request.getSocialUsername2()
				: null);
		userAcc.setSocialUsername3(request.getSocialUsername3() != null && !request.getSocialUsername3().isBlank()
				? request.getSocialUsername3()
				: null);
		userAcc.setSocialUsername4(request.getSocialUsername4() != null && !request.getSocialUsername4().isBlank()
				? request.getSocialUsername4()
				: null);
		userAcc.setSocialUsername5(request.getSocialUsername5() != null && !request.getSocialUsername5().isBlank()
				? request.getSocialUsername5()
				: null);
		userAcc.setPhoneCode(
				request.getPhoneCode() != null && !request.getPhoneCode().isBlank() ? request.getPhoneCode() : null);
		userAcc.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : "");
		userAcc.setAssignPettyCashAmount(
				request.getAssignPettyCashAmount() != null ? request.getAssignPettyCashAmount() : 0.0);
		userAcc.setPettyCashOpenBalance(
				request.getAssignPettyCashAmount() != null ? request.getAssignPettyCashAmount() : 0.0);
		userAcc.setIsSupervisor(
				request.getIsSupervisor() != null && !request.getIsSupervisor().isBlank() ? request.getIsSupervisor()
						: "0");
		userAcc.setUserType(
				request.getUserType() != null && !request.getUserType().isBlank() ? request.getUserType() : null);
		userAcc.setQueueEmailAddress(request.getQueueEmailAddress() != null && !request.getQueueEmailAddress().isBlank()
				? request.getQueueEmailAddress()
				: null);
		userAcc.setOverwriteBookingEmail(
				request.getOverwriteBookingEmail() != null && !request.getOverwriteBookingEmail().isBlank()
						? request.getOverwriteBookingEmail()
						: null);
		userAcc.setUserId(userId.longValue());
		userAcc.setEmployeeId(
				request.getEmployeeId() != null && !request.getEmployeeId().isBlank() ? request.getEmployeeId() : "0");
		userAcc.setCountry(request.getCountry() != null && !request.getCountry().isBlank() ? request.getCountry() : "");
		return userAcc;
	}

	public static BePermissionModel mapPermissonRequestToModel(PermissionRequestModel request) {
		BePermissionModel permissionModel = new BePermissionModel();
		if (request.getId() != null && request.getId() != 0)
			permissionModel.setId(request.getId());
		permissionModel.setCreatedBy(request.getCreatedBy());
		permissionModel.setKey(request.getKey());
		permissionModel.setDescription(request.getDescription());
		permissionModel.setName(request.getName());
		permissionModel.setGroupId(request.getGroupId());
		permissionModel.setGroupName(request.getGroupName());
		permissionModel.setIsSystem(request.getIsSystem());
		permissionModel.setSuspendedOn(request.getSuspendedOn());
		permissionModel.setDateCreated(new Date());
		if (Objects.nonNull(request.getOrgId()))
			permissionModel.setOrganizationId(request.getOrgId());
		else
			permissionModel.setOrganizationId(0L);
		return permissionModel;
	}

	public static PermissionRequestModel mapPModelToRequest(BePermissionModel request, Set<String> roles) {

		PermissionRequestModel permissionModel = new PermissionRequestModel();
		permissionModel.setId(request.getId());
		permissionModel.setCreatedBy(request.getCreatedBy());
		permissionModel.setKey(request.getKey());
		permissionModel.setDescription(request.getDescription());
		permissionModel.setName(request.getName());
		permissionModel.setGroupId(request.getGroupId());
		permissionModel.setGroupName(request.getGroupName());
		permissionModel.setIsSystem(request.getIsSystem());
		permissionModel.setSuspendedOn(request.getSuspendedOn() != null ? request.getSuspendedOn() : null);
		permissionModel.setDateCreated(request.getDateCreated() != null ? request.getDateCreated() : null);
		permissionModel.setDateUpdated(request.getDateUpdated() != null ? request.getDateUpdated() : null);
		permissionModel.setCreatedBy(request.getCreatedBy() != null ? request.getCreatedBy() : 0);
		permissionModel.setUpdatedBy(request.getUpdatedBy() != null ? request.getUpdatedBy() : 0);
		permissionModel.setOrgId(request.getOrganizationId());
		if (roles != null && !roles.isEmpty())
			permissionModel.setRoles(roles);
		else
			permissionModel.setRoles(Collections.emptySet());
		return permissionModel;

	}

	public static KeycloakUserModel convertUserDtoToKeycloakUserModel(UserDto dto) {

		KeycloakUserModel kcModel = new KeycloakUserModel();

		kcModel.setEmail(dto.getEmail().toLowerCase());
		kcModel.setUsername(dto.getEmail().toLowerCase());// in keycloak username must be uniq
		kcModel.setFirstName(dto.getFirstName());
		kcModel.setLastName(dto.getLastName());
		kcModel.setEnabled(true);
		return kcModel;
	}

	public static UserResponseModel generateUserResponse(BeUserModel userModel, BeUserDetailsModel userDeatilsRelation,
			List<BeUserRoleModel> roleModel) {
		UserResponseModel response = new UserResponseModel();
		/*
		 * set userInfo
		 */
		response.setUserId(userModel.getUserId());
		response.setEmployeeId(userModel.getEmployeeId());
		response.setOrgId(userModel.getOrgId());
		response.setUserName(userModel.getUserName());
		response.setEmail(userModel.getEmail());
		response.setEmailSecondary(userModel.getEmailSecondary());
		response.setPhoneNumber(userModel.getPhoneNumber());
		response.setPhoneSecondary(userModel.getPhoneSecondary());
		response.setCreatedDate(userModel.getCreatedDate());
		response.setVerifiedOn(userModel.getVerifiedOn());
		response.setLastSignedInOn(userModel.getLastSignedInOn());
		response.setResetSentOn(userModel.getResetSentOn());
		response.setDeletedOn(userModel.getDeletedOn());
		response.setSuspendedOn(userModel.getSuspendedOn());
		response.setEmployeeBookingAmount(userModel.getEmployeeBookingAmount());
		response.setCreditLimitAmount(userModel.getCreditLimitAmount());
		response.setRunningBalance(userModel.getRunningBalance());
		response.setUserUpdatePassword(userModel.getUserUpdatePassword());
		response.setStartDate(userModel.getStartDate());
		response.setEndDate(userModel.getEndDate());
		response.setStatus(userModel.getStatus());

		/*
		 * set user deatails info
		 */

		response.setId(userDeatilsRelation.getId());
		response.setFullName(userDeatilsRelation.getFullName());
		response.setSalutation(userDeatilsRelation.getSalutation());
		response.setFirstName(userDeatilsRelation.getFirstName());
		response.setDeptId(userDeatilsRelation.getDeptId());
		response.setLastName(userDeatilsRelation.getLastName());
		response.setDateOfBirth(userDeatilsRelation.getDateOfBirth());
		response.setGender(userDeatilsRelation.getGender());
		response.setPostalCode(userDeatilsRelation.getPostalCode());
		response.setCountry(userDeatilsRelation.getTimeZone());
		response.setLanguage(userDeatilsRelation.getLanguage());
		response.setTimeZone(userDeatilsRelation.getTimeZone());
		response.setCityTimezone(userDeatilsRelation.getCityTimezone());
		response.setPicture(userDeatilsRelation.getPicture());
		response.setLocationId(userDeatilsRelation.getLocationId());
		response.setLocationValue(userDeatilsRelation.getLocationValue());
		response.setLocationDescription(userDeatilsRelation.getLocationDescription());
		response.setCostCenterValue(userDeatilsRelation.getCostCenterValue());
		response.setCostCenterDescription(userDeatilsRelation.getCostCenterDescription());
		response.setCompanyValue(userDeatilsRelation.getCompanyValue());
		response.setCompanyDescription(userDeatilsRelation.getCompanyDescription());
		response.setShiftId(userDeatilsRelation.getShiftId());
		response.setIsManager(userDeatilsRelation.getIsManager());
		response.setManagerAccountId(userDeatilsRelation.getManagerAccountId());
		response.setEbsEmpId(userDeatilsRelation.getEbsEmpId());
		response.setEbsUserId(userDeatilsRelation.getEbsUserId());
		response.setSalesUserId(userDeatilsRelation.getSalesUserId());
		response.setSocialUsername1(userDeatilsRelation.getSocialUsername1());
		response.setSocialUsername2(userDeatilsRelation.getSocialUsername2());
		response.setSocialUsername3(userDeatilsRelation.getSocialUsername3());
		response.setSocialUsername4(userDeatilsRelation.getSocialUsername4());
		response.setSocialUsername5(userDeatilsRelation.getSocialUsername5());
		response.setPhoneCode(userDeatilsRelation.getPhoneCode());
		response.setAssignPettyCashAmount(userDeatilsRelation.getAssignPettyCashAmount());
		response.setPettyCashOpenBalance(userDeatilsRelation.getPettyCashOpenBalance());
		response.setIsSupervisor(userDeatilsRelation.getIsSupervisor());
		response.setUserType(userDeatilsRelation.getUserType());
		response.setQueueEmailAddress(userDeatilsRelation.getQueueEmailAddress());
		response.setOverwriteBookingEmail(userDeatilsRelation.getOverwriteBookingEmail());
		response.setRole(roleModel);
		response.setCountry(userDeatilsRelation.getCountry());
		return response;
	}

	public static BeUserModel modifyUserModel(UserDto userModel, Integer userId, BeUserModel userModeldbInfo) {
		userModeldbInfo.setUserId(userId);
		userModeldbInfo.setEmployeeId(userModel.getEmployeeId());
		userModeldbInfo.setOrgId(userModel.getOrgId());
		userModeldbInfo.setUserName(userModel.getUserName());
		userModeldbInfo.setEmail(userModel.getEmail());
		userModeldbInfo.setEmailSecondary(userModel.getEmailSecondary());
		userModeldbInfo.setPhoneNumber(userModel.getPhoneNumber());
		userModeldbInfo.setPhoneSecondary(userModel.getPhoneSecondary());
		userModeldbInfo.setVerifiedOn(userModel.getVerifiedOn());
		userModeldbInfo.setLastSignedInOn(userModel.getLastSignedInOn());
		userModeldbInfo.setResetSentOn(userModel.getResetSentOn());
		userModeldbInfo.setDeletedOn(userModel.getDeletedOn());
		userModeldbInfo.setSuspendedOn(userModel.getSuspendedOn());
		userModeldbInfo.setEmployeeBookingAmount(userModel.getEmployeeBookingAmount());
		userModeldbInfo.setCreditLimitAmount(userModel.getCreditLimitAmount());
		userModeldbInfo.setRunningBalance(userModel.getRunningBalance());
		userModeldbInfo.setUserUpdatePassword(userModel.getUserUpdatePassword());
		userModeldbInfo.setStartDate(userModel.getStartDate());
		userModeldbInfo.setEndDate(userModel.getEndDate());
		userModeldbInfo.setStatus(userModel.getStatus());
		return userModeldbInfo;
	}

	public static BeUserDetailsModel modifyUserDetailsModel(UserDto userModel, Integer userId,
			BeUserDetailsModel userModeldbInfo, Long id) {

		userModeldbInfo.setId(id);
		userModeldbInfo.setUserId(userId.longValue());

		userModeldbInfo.setFullName(userModel.getFullName());
		userModeldbInfo.setSalutation(userModel.getSalutation());
		userModeldbInfo.setFirstName(userModel.getFirstName());
		userModeldbInfo.setLastName(userModel.getLastName());
		userModeldbInfo.setDateOfBirth(userModel.getDateOfBirth());
		userModeldbInfo.setDeptId(userModel.getDeptId());
		userModeldbInfo.setGender(userModel.getGender());
		userModeldbInfo.setPostalCode(userModel.getPostalCode());
		userModeldbInfo.setCountry(userModel.getTimeZone());
		userModeldbInfo.setLanguage(userModel.getLanguage());
		userModeldbInfo.setTimeZone(userModel.getTimeZone());
		userModeldbInfo.setCityTimezone(userModel.getCityTimezone());
		userModeldbInfo.setPicture(userModel.getPicture());
		userModeldbInfo.setLocationId(userModel.getLocationId());
		userModeldbInfo.setLocationValue(userModel.getLocationValue());
		userModeldbInfo.setLocationDescription(userModel.getLocationDescription());
		userModeldbInfo.setCostCenterValue(userModel.getCostCenterValue());
		userModeldbInfo.setCostCenterDescription(userModel.getCostCenterDescription());
		userModeldbInfo.setCompanyValue(userModel.getCompanyValue());
		userModeldbInfo.setCompanyDescription(userModel.getCompanyDescription());
		userModeldbInfo.setShiftId(userModel.getShiftId());
		userModeldbInfo.setIsManager(userModel.getIsManager());
		userModeldbInfo.setManagerAccountId(userModel.getManagerAccountId());
		userModeldbInfo.setEbsEmpId(userModel.getEbsEmpId());
		userModeldbInfo.setEbsUserId(userModel.getEbsUserId());
		userModeldbInfo.setSalesUserId(userModel.getSalesUserId());
		userModeldbInfo.setSocialUsername1(userModel.getSocialUsername1());
		userModeldbInfo.setSocialUsername2(userModel.getSocialUsername2());
		userModeldbInfo.setSocialUsername3(userModel.getSocialUsername3());
		userModeldbInfo.setSocialUsername4(userModel.getSocialUsername4());
		userModeldbInfo.setSocialUsername5(userModel.getSocialUsername5());
		userModeldbInfo.setPhoneCode(userModel.getPhoneCode());
		userModeldbInfo.setAssignPettyCashAmount(userModel.getAssignPettyCashAmount());
		userModeldbInfo.setPettyCashOpenBalance(userModel.getPettyCashOpenBalance());
		userModeldbInfo.setIsSupervisor(userModel.getIsSupervisor());
		userModeldbInfo.setUserType(userModel.getUserType());
		userModeldbInfo.setQueueEmailAddress(userModel.getQueueEmailAddress());
		userModeldbInfo.setCountry(userModel.getCountry());
		userModeldbInfo.setOverwriteBookingEmail(userModel.getOverwriteBookingEmail());
		return userModeldbInfo;
	}

	public static BeUserModelResponse convertBeUserModelToResponse(BeUserModel model) {
		BeUserModelResponse response = new BeUserModelResponse();
		response.setUserId(model.getUserId());
		response.setIamId(model.getIamId());
		response.setEmployeeId(model.getEmployeeId());
		response.setOrgId(model.getOrgId());
		response.setUserName(model.getUserName());
		response.setEmail(model.getEmail());
		response.setEmailSecondary(model.getEmailSecondary());
		response.setPhoneNumber(model.getPhoneNumber());
		response.setPhoneSecondary(model.getPhoneSecondary());
		response.setCreatedDate(model.getCreatedDate());
		response.setVerifiedOn(model.getVerifiedOn());
		response.setLastSignedInOn(model.getLastSignedInOn());
		response.setResetSentOn(model.getResetSentOn());
		response.setDeletedOn(model.getDeletedOn());
		response.setSuspendedOn(model.getSuspendedOn());
		response.setEmployeeBookingAmount(model.getEmployeeBookingAmount());
		response.setCreditLimitAmount(model.getCreditLimitAmount());
		response.setRunningBalance(model.getRunningBalance());
		response.setUserUpdatePassword(model.getUserUpdatePassword());
		response.setStartDate(model.getStartDate());
		response.setEndDate(model.getEndDate());
		response.setStatus(model.getStatus());
		return response;
	}
}
