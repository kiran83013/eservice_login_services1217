package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.dto.UserDto;
import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.enums.SortType;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.service.BeUserService;

@RestController
public class UserBeController {

	@Autowired
	BeUserService userService;

	@PostMapping(value = "/create-beuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createUser(@RequestBody UserDto model, @RequestParam(required = false) Integer roleId) {
		return userService.createUser(model, roleId);
	}

	@GetMapping(value = "/get-be-user", produces = "application/json")
	public ResponseEntity<?> getUser(@RequestParam Integer userId) {
		return userService.getUser(userId);
	}

	@GetMapping(value = "/get-be-user-by-emp", produces = "application/json")
	public ResponseEntity<?> getUserByEmp(@RequestParam String employeeId) {
		return userService.getUserByEmp(employeeId);
	}

//	@GetMapping(value = "/get-be-users", produces = "application/json")
//	public ResponseEntity<?> getUsers() {
//		return userService.getUsers();
//	}
	@GetMapping(value = "/get-be-users", produces = "application/json")
	public ResponseEntity<?> getUsers(Long orgId) {
		return userService.getUsers(orgId);
	}

	@GetMapping(value = "/get-be-user-by-name", produces = "application/json")
	public ResponseEntity<?> getUserByName(@RequestParam String name) {
		return userService.getUserByName(name);
	}

//	@PostMapping(value = "/send-mail")
//	public ResponseEntity<?> sendMail(@RequestBody MailRequestModel model)
//			throws UnsupportedEncodingException, CannotSendEmailException {
//		return userService.sendMail(model);
//	}

	@PutMapping(value = "/modify-beuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> modifyUser(@RequestBody UserDto model, @RequestParam Integer userId,
			@RequestParam(required = false) Integer roleId) {
		return userService.modifyUser(model, userId, roleId);
	}

	@PostMapping(value = "/search-user")
	public ResponseEntity<?> searchUser(@RequestBody UserSearchRequestDto model) {
		return userService.searchUser(model);
	}

	@PostMapping(value = "/create-be-rootuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createRootUser(@RequestBody UserDto model) {
		return userService.createRootUser(model);
	}

	@GetMapping(value = "/list-get-user-pagination")
	public APIResponsePaging getPagenationByOrganization(@RequestParam Integer orgId,
			@RequestParam(required = false) String userName, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "userId") String sortBy,
			@RequestParam(defaultValue = "asc", required = false) SortType sortType) {
		return userService.getPagenationByOrganization(orgId, userName, pageNo, pageSize, sortBy, sortType);
	}

	@PostMapping("/password-email")
	public ResponseEntity<?> forgotPassword(@RequestParam(required = false) String email) throws Exception {
		return userService.forgotPassword(email);
	}
}
