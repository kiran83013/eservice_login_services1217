package com.travel.travtronics.service;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travel.travtronics.api.EncryptionModel;



@Service
public class EncryptionService {

	@Autowired
	EncryptionModel properties;

	private IvParameterSpec generateIvParameterSpec() {
		String key = properties.getPublicKey();
		byte[] ss = key.getBytes();
		return new IvParameterSpec(ss);
	}

	private SecretKeySpec generateKey() throws Exception {
		SecretKeySpec key = new SecretKeySpec(properties.getKeyValue().getBytes("UTF-8"), "AES");
		return key;
	}

	public String decryptBody(String encryptedData) throws Exception {
		Cipher cipher = Cipher.getInstance(properties.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, generateKey(), generateIvParameterSpec());
		byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(encryptedData));
		return new String(original);
	}

	public String encryptBody(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(properties.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(), generateIvParameterSpec());
		byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
		return new String(new String(Base64.getMimeEncoder().encode(encrypted)));
	}

	public ResponseEntity<String> encryptBodyService(String data) throws Exception {
		Cipher cipher = Cipher.getInstance(properties.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(), generateIvParameterSpec());
		byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
		return new ResponseEntity<String>((new String(Base64.getMimeEncoder().encode(encrypted))), HttpStatus.OK);
	}

	public ResponseEntity<String> decryptBodyService(String encryptedData) throws Exception {
		Cipher cipher = Cipher.getInstance(properties.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, generateKey(), generateIvParameterSpec());
		byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(encryptedData));
		return new ResponseEntity<String>(new String(original), HttpStatus.OK);
	}
}
