package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.dto.GetToken;
import com.travel.travtronics.dto.LoginRequestModel;
import com.travel.travtronics.dto.RefreshTokenRequestModel;
import com.travel.travtronics.service.AuthenticationService;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthenticationService authService;

	@PostMapping(value = "/obtain-token", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> obtainToken(@RequestBody GetToken model) {
		return authService.obtainToken(model);
	}

	@PostMapping(value = "/refresh-token", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestModel model) {
		return authService.refreshToken(model);
	}

	@PutMapping(value = "/update-password")
	public ResponseEntity<?> updatePassword(@RequestParam String iamId, @RequestParam String password) {
		return authService.updateUserPassword(iamId, password);
	}

	@PostMapping(value = "be/user-login", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> loginBeUser(@RequestBody LoginRequestModel model) {
		return authService.loginBeUser(model);
	}

	@PostMapping(value = "be/user-logout/{userId}")
	public ResponseEntity<?> beUserLogout(@PathVariable Integer userId) {
		return authService.beUserLogout(userId);
	}



}
