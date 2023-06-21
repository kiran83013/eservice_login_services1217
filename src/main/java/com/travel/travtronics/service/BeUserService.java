
package com.travel.travtronics.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.dto.ApiConverter;
import com.travel.travtronics.dto.EmailModel;
import com.travel.travtronics.dto.MailRequestModel;
import com.travel.travtronics.dto.UserDto;
import com.travel.travtronics.dto.UserResponseModel;
import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.dto.UserSearchResponse;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.kc.model.KeycloakUserModel;
import com.travel.travtronics.model.BeUserDetailsModel;
import com.travel.travtronics.model.BeUserModel;
import com.travel.travtronics.model.BeUserRoleModel;
import com.travel.travtronics.model.BeUserRoleRelation;
import com.travel.travtronics.repository.BeUserCustomPaginationRepository;
import com.travel.travtronics.repository.BeUserDetailsModelRepository;
import com.travel.travtronics.repository.BeUserRepository;
import com.travel.travtronics.repository.BeUserRoleRelationRepository;
import com.travel.travtronics.repository.BeUserRoleRepository;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.response.BeUserModelResponse;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Service
public class BeUserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BeUserRepository userRepository;
	@Autowired
	AuthenticationService authService;
	@Autowired
	BeUserDetailsModelRepository userDetailsRepository;

	@Autowired
	BeRolePermissionService rolePermissionService;
	@Autowired
	BeUserRoleRepository roleRepository;

	@Autowired
	BeUserRoleRelationRepository userRoleRelationRepository;

	@Value("${com.travel.keycloak.default-password}")
	private String travelDefaultPassword;

	@Value("${com.travel.tech.optionaudit.email.resetPassword}")
	private String resetPassword;

	@Autowired
	CommonSyncService commonSyncService;

	public String createUserInKeycloak(UserDto dto) {

		KeycloakUserModel kcUserModel = ApiConverter.convertUserDtoToKeycloakUserModel(dto);

		ResponseEntity<String> createUser = authService.createUser(kcUserModel);

		logger.info("user-created-in-keycloak");

		String iamId = authService.getUserIdFromKeyCloak(createUser);

		logger.info("fetch-user-id-from-keycloak");
		if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {

			/*
			 * sent password to a user in keycloak
			 */
			authService.updatePassword(iamId, dto.getPassword());
			logger.info("password-updated-to-a-user");

		} else {
			authService.updatePassword(iamId, travelDefaultPassword);
			logger.info("password-updated-to-a-user");
		}

		if (dto.getRole() != null && !dto.getRole().isEmpty()) {
			/*
			 * assign role to a user in keycloak
			 */
			authService.assignRoleToUser(dto.getRole(), iamId);

			logger.info("role-updated-to-a-user");
		}
		return iamId;

	}
	
	public ResponseEntity<?> createRootUser(UserDto model) {
		Optional<BeUserModel> emailValidation = userRepository.findByEmailOrPhoneNumber(model.getEmail(),
				model.getPhoneNumber());
		if (!emailValidation.isPresent()) {

			BeUserModel user = ApiConverter.convertDtoToModel(model);
			/*
			 * create user info in db
			 */
			BeUserModel savedUser = userRepository.save(user);

			logger.info("user-info-saved-in database");

			rolePermissionService.createUserRoleRelation(savedUser.getUserId(),
					roleRepository.getRoleIdByRoleName("RootSysAdmin").get().intValue());
			logger.info("user-role-relation-in database");

			/*
			 * save user extra info in db
			 */
			BeUserDetailsModel userDeatilsModel = ApiConverter.convertDtoToUserDeatilsModel(model,
					savedUser.getUserId());

			userDetailsRepository.save(userDeatilsModel);

			/*
			 * assign keycloak role
			 */

			model.setRole(new String("beUser"));

			/*
			 * create a user in keycloak and return keycloak id
			 */
			String iamId = createUserInKeycloak(model);

			logger.info("user-info-saved-in kc");

			/*
			 * update iamId in user's info
			 */

			userRepository.updateIamId(iamId, savedUser.getUserId());

			logger.info("iamid is stored in user");

			return new ResponseEntity<>(savedUser, HttpStatus.OK);

		} else {
			return new ResponseEntity<>("{\"message\":\"user-already-registered-in-our-system\"}", HttpStatus.CONFLICT);
		}

	}

	public ResponseEntity<?> createUser(UserDto model, Integer roleId) {

		try {
			Optional<BeUserModel> emailValidation = userRepository.findByEmail(model.getEmail());
			if (emailValidation.isPresent()) {
				return new ResponseEntity<>("{\"message\":\"email already registered in our system\"}",
						HttpStatus.CONFLICT);
			}
			Optional<BeUserModel> phoneValidation = userRepository.findByPhoneNumber(model.getPhoneNumber());
			if (phoneValidation.isPresent()) {
				return new ResponseEntity<>("{\"message\":\"phone already registered in our system\"}",
						HttpStatus.CONFLICT);
			}
			Optional<BeUserModel> usernameValidation = userRepository.findByUserName(model.getUserName());
			if (usernameValidation.isPresent()) {
				return new ResponseEntity<>("{\"message\":\"username already registered in our system\"}",
						HttpStatus.CONFLICT);
			}
			BeUserModel user = ApiConverter.convertDtoToModel(model);
			/*
			 * create user info in db
			 */
			BeUserModel savedUser = userRepository.save(user);

			if (roleId != null && roleId != 0) {
				rolePermissionService.createUserRoleRelation(savedUser.getUserId(), roleId);
				logger.info("user-role-relation-in database");
			}

			/*
			 * save user extra info in db
			 */
			BeUserDetailsModel userDeatilsModel = ApiConverter.convertDtoToUserDeatilsModel(model,
					savedUser.getUserId());

			userDetailsRepository.save(userDeatilsModel);

			logger.info("user-info-saved-in database");

			/*
			 * assign keycloak role
			 */

			model.setRole(new String("beUser"));

			/*
			 * create a user in keycloak and return keycloak id
			 */
			String iamId = createUserInKeycloak(model);

			logger.info("user-info-saved-in kc");

			/*
			 * update iamId in user's info
			 */

			userRepository.updateIamId(iamId, savedUser.getUserId());

			logger.info("iamid is stored in user");

			commonSyncService.sendPasswordResetMail(savedUser.getEmail());

			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("{\"message\":\"something went wrong and try once again....!!!\"}",
					HttpStatus.EXPECTATION_FAILED);
		}

	}

	public ResponseEntity<?> getUser(Integer userId) {

		BeUserModel model = userRepository.findByUserId(userId);
		if (Objects.isNull(model))
			return new ResponseEntity<>("{\"message\":\"user-not-avilable-in-our-system\"}", HttpStatus.NOT_FOUND);
		else {
			Optional<BeUserDetailsModel> userDeatilsInfo = userDetailsRepository
					.findByUserId(model.getUserId().longValue());
			List<BeUserRoleModel> roleInfo = roleRepository.getRoleInfo(model.getUserId());

			UserResponseModel generateUserResponse = ApiConverter.generateUserResponse(model, userDeatilsInfo.get(),
					roleInfo);
			return new ResponseEntity<>(generateUserResponse, HttpStatus.OK);
		}

	}

//	public ResponseEntity<?> getUsers() {
//
//		return new ResponseEntity<>(userRepository.findAllList(), HttpStatus.OK);
//	}

	public ResponseEntity<?> getUserByName(String name) {

		List<BeUserModel> searchedUsers = userRepository.findByUserNameContainingIgnoreCase(name);
		return new ResponseEntity<>(searchedUsers, HttpStatus.OK);
	}

	@Value("${com.travel.tech.optionaudit.url.quoteSendUrl}")
	private String quoteSendUrl;

	@Autowired
	EmailService emailService;

	public ResponseEntity<?> sendMail(MailRequestModel model)
			throws UnsupportedEncodingException, CannotSendEmailException {

		EmailModel emailModel = new EmailModel();

		String userName = null;
		if (model.getFirstname() != null && model.getLastName() != null)
			userName = model.getFirstname() + "" + model.getLastName();
		if (model.getFirstname() == null && model.getLastName() == null)
			userName = "";
		if (model.getFirstname() != null && model.getLastName() == null)
			userName = model.getFirstname();
		emailModel.setNameTo("User name:" + userName);
		emailModel.setSendTo(model.getEmail());

		emailModel.setSubject("SinnUp");
		emailModel.setNameTemplateHtml("sendQuoteToUserMail.html");
		Map<String, Object> objectModel = new HashMap<>();
		// objectModel.put("quoteSendUrl", quoteSendUrl + "?19906");
		emailModel.setObjectModel(objectModel);

		emailService.sendEmail(emailModel);

		return new ResponseEntity<>("{\"message\":\"email-sent-successfully\"}", HttpStatus.OK);
	}

	public ResponseEntity<?> modifyUser(UserDto dto, Integer userId, Integer roleId) {
		BeUserModel model = userRepository.findByUserId(userId);
		if (Objects.isNull(model))
			return new ResponseEntity<>("{\"message\":\"user-not-avilable-in-our-system\"}", HttpStatus.NOT_FOUND);
		else {
			/*
			 * save user details
			 */
			BeUserModel userModelInfo = ApiConverter.modifyUserModel(dto, model.getUserId(), model);
			userRepository.save(userModelInfo);
			logger.info("modify-user-info");

			Optional<BeUserDetailsModel> userDeatilsModel = userDetailsRepository.findByUserId(userId.longValue());
			/*
			 * save user deatils info
			 */
			BeUserDetailsModel userDeatilsInfo = ApiConverter.modifyUserDetailsModel(dto, model.getUserId(),
					userDeatilsModel.get(), userDeatilsModel.get().getId());

			userDetailsRepository.save(userDeatilsInfo);
			logger.info("modify-user-deatils-info");

			/*
			 * update role relation info
			 */
			if (roleId != null && roleId != 0) {
				List<BeUserRoleRelation> roleRelationInfo = userRoleRelationRepository.findAllByUserId(userId);

				roleRelationInfo.forEach(rel -> {
					rel.setRoleId(roleId);
					rel.setUserId(userId);
					userRoleRelationRepository.save(rel);
					logger.info("user-role-relation-info-modified");
				});
			}

			/*
			 * update-password-in-keycloak
			 */
			if (Objects.nonNull(dto.getPassword()) && !dto.getPassword().trim().isEmpty() && model.getIamId() != null
					&& !model.getIamId().trim().isEmpty()) {
				authService.updatePassword(model.getIamId(), dto.getPassword());
				logger.info("password-updated-to-a-user");
			}
			return new ResponseEntity<>("{\"message\":\"user-info-modified-in-our-system\"}", HttpStatus.OK);
		}

	}

	public ResponseEntity<?> getUserByEmp(String employeeId) {
		List<BeUserModel> userInfo = userRepository.findByEmployeeIdAndEmployeeIdIsNotNull(employeeId);
		if (userInfo.isEmpty())
			return new ResponseEntity<>("{\"message\":\"user-not-avilable-in-our-system\"}", HttpStatus.NOT_FOUND);
		else {
			List<UserResponseModel> collectedUserResponse = userInfo.stream().map(user -> {
				Optional<BeUserDetailsModel> userDeatilsInfo = userDetailsRepository
						.findByUserId(user.getUserId().longValue());

				List<BeUserRoleModel> roleInfo = roleRepository.getRoleInfo(user.getUserId());
				return ApiConverter.generateUserResponse(user, userDeatilsInfo.get(), roleInfo);
			}).collect(Collectors.toCollection(ArrayList::new));
			return new ResponseEntity<>(collectedUserResponse, HttpStatus.OK);
		}

	}

	public ResponseEntity<?> searchUser(UserSearchRequestDto model) {

		if (model.equals(new UserSearchRequestDto()) == true) {
			return new ResponseEntity<>("{\"message\":\"provide-atleast-one-parameter-for-search-criteria\"}",
					HttpStatus.BAD_REQUEST);
		}

		List<UserSearchResponse> searchResponse = userRoleRelationRepository.findBySearchParameters(model);
		return new ResponseEntity<>(searchResponse, HttpStatus.OK);
	}

	

	public ResponseEntity<?> getUsers(Long orgId) {
		return new ResponseEntity<>(userRepository.findByOrgId(orgId), HttpStatus.OK);
	}

	@Autowired
	BeUserCustomPaginationRepository beUserCustomPaginationRepository;

	public APIResponsePaging getPagenationByOrganization(Integer orgId, String userName, int pageNo, int pageSize,
			String sortBy, SortType sortType) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);

//		if (orgId != null) {
//			Page<BeUserModel> findByOrganizationId = userRepository.findByOrgId(orgId, pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findByOrganizationId.getContent(),
//					new ArrayList<>(), findByOrganizationId.getNumber(), findByOrganizationId.getTotalElements(),
//					findByOrganizationId.getTotalPages());
//		} else {
//			Page<BeUserModel> findAll = userRepository.findAll(pageable);
//			return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), findAll.getContent(),
//					new ArrayList<>(), findAll.getNumber(), findAll.getTotalElements(), findAll.getTotalPages()); 
//		} new call
//		if (orgId != null) {
		Page<BeUserModel> findByOrganizationId = beUserCustomPaginationRepository.fetchPagination(orgId, userName,
				pageable, sortBy, sortType);
		Page<BeUserModelResponse> item = findByOrganizationId.map(model -> {
			BeUserModelResponse response = ApiConverter.convertBeUserModelToResponse(model);
			Optional<String> orgName = userRepository.getOrgName(response.getOrgId());
			Optional<String> userNames = userRepository.getUserName(response.getUserName());
			if (orgName.isPresent())
				response.setOrgName(orgName.get());
			if (userNames.isPresent())
				response.setUserName(userNames.get());
			return response;
		});
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), item.getContent(), new ArrayList<>(),
				item.getNumber(), item.getTotalElements(), item.getTotalPages());
	}

	public ResponseEntity<?> forgotPassword(String email)
			throws UnsupportedEncodingException, CannotSendEmailException {
		BeUserModel userOptional = userRepository.findByEmailAndIamIdIsNotNull(email);
		if (Objects.nonNull(userOptional)) {

			EmailModel emailModel = new EmailModel();
			emailModel.setNameTo(userOptional.getUserName());
			emailModel.setSendTo(email);
			emailModel.setSubject("Reset Password");
			emailModel.setNameTemplateHtml("resetPassword.html");
			Map<String, Object> objectModel = new HashMap<>();
			objectModel.put("resetPasswordUrl", resetPassword + userOptional.getIamId());
			objectModel.put("userName", userOptional.getUserName());
			emailModel.setObjectModel(objectModel);

			emailService.sendEmail(emailModel);

			return new ResponseEntity<>(
					"{\"message\":\"Check Your Email and Click on the link to reset your password.\"}", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("{\"message\":\"this email is not Registered with us, Please try again!!\"}",
					HttpStatus.NOT_FOUND);
		}
	}
}
