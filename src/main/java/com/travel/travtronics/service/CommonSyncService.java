package com.travel.travtronics.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.travel.travtronics.dto.EmailModel;
import com.travel.travtronics.model.BeUserModel;
import com.travel.travtronics.repository.BeUserRepository;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Service
public class CommonSyncService {

	@Autowired
	BeUserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Value("${com.travel.tech.optionaudit.email.resetPassword}")
	private String resetPassword;

	/*
	 * send password reset email to user on user registration
	 */
	@Async("customExecutor")
	public ResponseEntity<?> sendPasswordResetMail(String email)
			throws UnsupportedEncodingException, CannotSendEmailException {

		BeUserModel user = userRepository.findByEmailAndIamIdIsNotNull(email);
		if (Objects.nonNull(user)) {

			EmailModel emailModel = new EmailModel();

			emailModel.setNameTo(WordUtils.capitalize(user.getUserName()));
			emailModel.setSendTo(email);
			emailModel.setSubject("Reset Password");
			emailModel.setNameTemplateHtml("resetPassword.html");
			Map<String, Object> objectModel = new HashMap<>();
			objectModel.put("resetPasswordUrl", resetPassword + user.getIamId());
			objectModel.put("userName", user.getUserName());
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
