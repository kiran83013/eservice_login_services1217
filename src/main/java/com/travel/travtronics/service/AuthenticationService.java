package com.travel.travtronics.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.api.KeyCloakRolesModel;
import com.travel.travtronics.api.PropertiesModel;
import com.travel.travtronics.config.RequestResponseLoggingInterceptor;
import com.travel.travtronics.dto.AttrModel;
import com.travel.travtronics.dto.GetToken;
import com.travel.travtronics.dto.LoginRequestModel;
import com.travel.travtronics.dto.LoginResponseModel;
import com.travel.travtronics.dto.RefreshTokenRequestModel;
import com.travel.travtronics.dto.RefreshTokenResponseModel;
import com.travel.travtronics.kc.model.CreadentialsModel;
import com.travel.travtronics.kc.model.GetUserModel;
import com.travel.travtronics.kc.model.KeycloakUserModel;
import com.travel.travtronics.kc.model.TokenModel;
import com.travel.travtronics.kc.model.UserGroupModel;
import com.travel.travtronics.model.BePermissionModel;
import com.travel.travtronics.model.BeUserModel;
import com.travel.travtronics.model.BeUserRoleModel;
import com.travel.travtronics.model.UserExtraModel;
import com.travel.travtronics.repository.BePermissionModelRepository;
import com.travel.travtronics.repository.BeRolePermissionRelationRepository;
import com.travel.travtronics.repository.BeUserRepository;
import com.travel.travtronics.repository.BeUserRoleRelationRepository;
import com.travel.travtronics.repository.BeUserRoleRepository;
import com.travel.travtronics.repository.PermissionEntityRepository;

@Service
public class AuthenticationService {

	@Autowired
	PropertiesModel kcModel;

	@Autowired
	KeyCloakRolesModel rolesModel;
	@Autowired
	BeUserRepository userRepository;

	@Autowired
	BeUserRoleRelationRepository beUserRoleRelationRepository;

	@Autowired
	BeUserRoleRepository beUserRoleRepository;

	@Autowired
	BeRolePermissionRelationRepository beRolePermissionRepository;

	@Autowired
	BePermissionModelRepository bePermissionRepository;

	@Autowired
	BeUserRoleRelationRepository beUserRoleRepostory;

	@Autowired
	PermissionEntityRepository permissionEntityRepository;

	private RestTemplate restTemplate;

	@Autowired
	public AuthenticationService(RestTemplateBuilder builder) {
		restTemplate = builder.build();

		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(clientHttpRequestFactory);
		clientHttpRequestFactory.setOutputStreaming(false);
		restTemplate.setRequestFactory(factory);
		restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
	}

	/*
	 * get admin token from keycloak
	 */
	public ResponseEntity<?> obtainToken(GetToken model) {
		String url = kcModel.getUrlKeycloakHost() + "auth/realms/" + kcModel.getRealm()
				+ "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", model.getGrant_type());
		map.add("client_id", model.getClient_id());
		map.add("username", model.getUser_name());
		map.add("password", model.getPassword());
		map.add("client_secret", model.getClient_secret());
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<TokenModel> postForEntity = restTemplate.postForEntity(url, request, TokenModel.class);

		return new ResponseEntity<>(postForEntity.getBody(), HttpStatus.OK);
	}

	/*
	 * get userInfo from keycloak by email
	 */
	public ResponseEntity<?> getUserInfoByEmail(String email) {

		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users?email=" + email;
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());
		HttpEntity<?> entity = new HttpEntity<>(headers);

		try {

			ResponseEntity<GetUserModel[]> exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
					GetUserModel[].class);

			List<GetUserModel> convertValue = new ObjectMapper().convertValue(exchange.getBody(),
					new TypeReference<List<GetUserModel>>() {
					});
			return new ResponseEntity<>(convertValue.get(0).getId(), HttpStatus.OK);

		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * update password to a user in keycloak
	 */
	public ResponseEntity<?> updatePassword(String iamId, String password) {
		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/" + iamId
				+ "/reset-password";
		CreadentialsModel passwordModel = new CreadentialsModel();
		passwordModel.setTemporary(false);
		passwordModel.setType(new String("password"));
		passwordModel.setValue(password);

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<>(passwordModel, headers);

		restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		return new ResponseEntity<>("{\"message\":\"password-updated-successfully\"}", HttpStatus.OK);
	}

	/*
	 * create a user in keycloak
	 */
	public ResponseEntity<String> createUser(KeycloakUserModel model) {

		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users";
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<KeycloakUserModel> entity = new HttpEntity<>(model, headers);
		try {

			return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public String getUserIdFromKeyCloak(ResponseEntity<String> response) {

		String location = response.getHeaders().get("Location").get(0).toString();
		String[] locationSplit = location.split("/");
		String keycloakId = locationSplit[8];
		return keycloakId;
	}

	/*
	 * refresh token
	 */
	public ResponseEntity<?> refreshToken(RefreshTokenRequestModel model) {
		String url = kcModel.getUrlKeycloakHost() + "auth/realms/" + kcModel.getRealm()
				+ "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "refresh_token");
		map.add("client_id", kcModel.getClientId());
		map.add("client_secret", kcModel.getClient_secret());
		map.add("refresh_token", model.getToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		return new ResponseEntity<>(restTemplate.postForEntity(url, request, RefreshTokenResponseModel.class).getBody(),
				HttpStatus.OK);
	}

	/*
	 * get Roles To a user
	 */
	public List<UserGroupModel> getUserRolesInfo(String iamId) {

		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/" + iamId
				+ "/groups";

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());
		headers.setContentType(MediaType.APPLICATION_JSON);
		ObjectMapper mapper = new ObjectMapper();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<List> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

		List<UserGroupModel> convertValue = mapper.convertValue(exchange.getBody(),
				new TypeReference<List<UserGroupModel>>() {
				});
		return convertValue;
	}

	/*
	 * assign a role to user
	 */
	public ResponseEntity<?> assignRoleToUser(String role, String iamId) {
		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/" + iamId
				+ "/groups/";
		if (role.equalsIgnoreCase("admin")) {
			url += rolesModel.getAdminRoleId();

		}

		if (role.equalsIgnoreCase("feUser")) {
			url += rolesModel.getFeUserRoleId();
		}

		if (role.equalsIgnoreCase("beUser")) {
			url += rolesModel.getBeUserRoleId();
		}

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());
		// headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			restTemplate.put(url, entity);

			return new ResponseEntity<>("{\"message\":\"role-assigned\"}", HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * delete a role to user
	 */
	public ResponseEntity<?> deleteRolesToUser(String role, String iamId) {
		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/" + iamId
				+ "/groups/";
		if (role.equalsIgnoreCase("admin")) {
			url += rolesModel.getAdminRoleId();

		}

		if (role.equalsIgnoreCase("feUser")) {
			url += rolesModel.getFeUserRoleId();
		}

		if (role.equalsIgnoreCase("beUser")) {
			url += rolesModel.getBeUserRoleId();
		}
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());

		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
			return new ResponseEntity<>("{\"message\":\"role-deleted\"}", HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<?> loginBeUser(LoginRequestModel model) {
		BeUserModel user = userRepository.findByEmailAndIamIdIsNotNull(model.getEmail());
		if (Objects.isNull(user))
			return new ResponseEntity<>("{\"message\":\"user-not-available-in-our-system\"}", HttpStatus.NOT_FOUND);
		else {

			String url = kcModel.getUrlKeycloakHost() + "auth/realms/" + kcModel.getRealm()
					+ "/protocol/openid-connect/token";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "password");
			map.add("client_id", kcModel.getClientId());
			map.add("username", model.getEmail());
			map.add("password", model.getPassword());
			map.add("client_secret", kcModel.getClient_secret());
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

			/*
			 * login user
			 */
			ResponseEntity<LoginResponseModel> postForEntity = restTemplate.postForEntity(url, request,
					LoginResponseModel.class);

			if (Objects.nonNull(user.getWtId())
					&& (Objects.nonNull(user.getWtProfile()) || user.getWtProfile().length() != 0)
					&& Objects.nonNull(user.getWtFlag()) && user.getWtFlag() != true) {
				System.out.println("---wt details-alterations------");
				Map<String, Object> attr = new HashMap<>();

				attr.put("wtId", user.getWtId());
				attr.put("wtProfile", user.getWtProfile());
				attr.put("userId", user.getUserId());

				addAttributes(user.getIamId(), attr);

				user.setWtFlag(true);
				userRepository.save(user);
			}

			List<UserGroupModel> userRolesInfo = getUserRolesInfo(user.getIamId());

			LoginResponseModel loginResponse = new LoginResponseModel();
			loginResponse = postForEntity.getBody();
			// loginResponse.setAccess_token(generateTokenModel.getToken());
			loginResponse.setRoles(userRolesInfo.stream().map(role -> role.getName()).collect(Collectors.toList()));
			loginResponse.setUserId(user.getUserId());
			// loginResponse.setFullName(user.getUserName());

//			loginResponse.setOrgId(user.getOrgId());
//
//			Optional<String> orgNameByOrgId = beUserRoleRepostory.getOrgNameByOrgId(user.getOrgId());
//
//			loginResponse.setOrganization(orgNameByOrgId.isPresent() ? orgNameByOrgId.get() : "");
			loginResponse.setEmail(user.getEmail());
			loginResponse.setPhoneNo(user.getPhoneNumber());

//			Optional<String> empName = userRepository.getEmpName(user.getEmployeeId());
//			loginResponse.setEmpName(empName.isPresent() ? empName.get() : "");

			/*
			 * temporarily sent static values
			 */

			UserExtraModel userInformation = permissionEntityRepository.getUserInfo(user.getUserId());

			if (Objects.nonNull(userInformation)) {

				loginResponse.setOrgId(user.getOrgId());
				loginResponse.setEmpId(user.getEmployeeId());
				loginResponse.setOrganization(userInformation.getOrgName());
				loginResponse.setEmpName(userInformation.getEmpName());
				loginResponse.setFullName(userInformation.getFullName());

				loginResponse.setMenu(userInformation.getMenu());
				
			}

			Optional<String> customerInfo = beUserRoleRepostory.getCustomerInfo(1);
			loginResponse.setCustomer(customerInfo.isPresent() ? customerInfo.get() : "");
			loginResponse.setCustomerId(1);
			// loginResponse.setEmpId(user.getEmployeeId());

			/*
			 * fetch role and permission keys
			 */

			List<Integer> roleIds = new ArrayList<>();
			Set<String> roleKeyList = beUserRoleRelationRepository.findAllByUserId(Integer.valueOf(user.getUserId()))
					.stream().map(role -> {

						Optional<BeUserRoleModel> roleInfo = beUserRoleRepository
								.findByRoleId(Long.valueOf(role.getRoleId()));
						String roleKey = null;
						if (roleInfo.isPresent()) {
							roleIds.add(roleInfo.get().getId().intValue());
							roleKey = roleInfo.get().getKey();
						} else
							roleKey = "";

						return roleKey;
					}).filter(key -> !key.trim().isBlank() && Objects.nonNull(key)).collect(Collectors.toSet());

			loginResponse.setRoleKeys(roleKeyList);

			Set<String> permissionKeys = beRolePermissionRepository.findByRoleIdIn(roleIds).stream().map(permission -> {

				Optional<BePermissionModel> permissionInfo = bePermissionRepository
						.findByPermissionId(permission.getPermissionId());
				String permissionKey = null;
				if (permissionInfo.isPresent())
					permissionKey = permissionInfo.get().getKey();
				else
					permissionKey = "";
				return permissionKey;

			}).filter(key -> !key.trim().isBlank() && Objects.nonNull(key)).collect(Collectors.toSet());

			loginResponse.setPermissionKeys(permissionKeys);

			return new ResponseEntity<>(loginResponse, HttpStatus.OK);
		}

	}

	public ResponseEntity<?> beUserLogout(Integer userId) {
		BeUserModel user = userRepository.findByUserIdAndIamIdIsNotNull(userId);
		if (Objects.isNull(user))
			return new ResponseEntity<>("{\"message\":\"some-thing-went-wrong\"}", HttpStatus.NOT_FOUND);
		else {
			String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/"
					+ user.getIamId() + "/logout";
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());

			HttpEntity<?> request = new HttpEntity<>(headers);
			String logout = null;
			ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
			if (postForEntity.getStatusCodeValue() == 204)
				logout = "{\"message\":\"logged-out-successfully....\"}";
			else
				logout = "{\"message\":\"unable-to-logout...\"}";
			return new ResponseEntity<>(logout, HttpStatus.OK);
		}

	}

	public ResponseEntity<?> updateUserPassword(String iamId, String password) {
		BeUserModel user = userRepository.findByIamId(iamId);

		if (Objects.isNull(user)) {
			return new ResponseEntity<>("{\"message\":\"invalid user information received\"}", HttpStatus.NOT_FOUND);

		} else if (Objects.isNull(user.getPasswordUpdatedAt())) {

			user.setPasswordUpdatedAt(String.valueOf(LocalDate.now()));
			userRepository.save(user);
			return updatePassword(iamId, password);
		} else {

			LocalDate recentPasswordUpdatedDate = LocalDate.parse(user.getPasswordUpdatedAt());

			if (LocalDate.now().compareTo(recentPasswordUpdatedDate) == 0) {
				return new ResponseEntity<>(
						"{\"message\":\"your password reset request has been rejected ..try after one day\"}",
						HttpStatus.ALREADY_REPORTED);
			} else {
				user.setPasswordUpdatedAt(String.valueOf(LocalDate.now()));
				userRepository.save(user);
				return updatePassword(iamId, password);
			}

		}
	}

	public ResponseEntity<?> addAttributes(String iamId, Map<String, Object> map) {
		// String url =
		// "http://192.178.10.132:8080/auth/admin/realms/Travtronics/users/" + iamId;

		String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/" + iamId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());
//		Map<String, Object> map = new HashMap<>();
//
//		map.put("wtId", 123);
//		map.put("wtProfile", "Profile");
//		map.put("userId", 123);

		AttrModel attr = new AttrModel();
		attr.setAttributes(map);

		HttpEntity<?> request = new HttpEntity<>(attr, headers);

		restTemplate.put(url, request, String.class);
		return ResponseEntity.ok("done");

	}

}
