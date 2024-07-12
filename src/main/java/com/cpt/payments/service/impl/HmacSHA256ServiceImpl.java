package com.cpt.payments.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.exeption.ValidationException;
import com.cpt.payments.service.HmacSHA256Service;

@Component
public class HmacSHA256ServiceImpl implements HmacSHA256Service {

	private String secretKey = "THIS_IS_MY_SECRET";

	@Override
	public String generateSignature(String data) {
		try {
			// Create a SecretKeySpec object from the secret key
			SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

			// Initialize the HMAC-SHA256 Mac instance
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(keySpec);

			// Compute the HMAC-SHA256 signature
			byte[] signatureBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

			// Encode the signature in Base64
			String signature = Base64.getEncoder().encodeToString(signatureBytes);

			System.out.println("HMAC-SHA256 Signature: " + signature);

			return signature;
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();

			System.out.println("Enourced exception while generating signature.");

			throw new ValidationException(ErrorCodeEnum.SIGNATURE_GENERATION_ERROR.getErrorCode(),
					ErrorCodeEnum.SIGNATURE_GENERATION_ERROR.getErrormessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public boolean verifySignature(String data, String incomingSignature) {

		String generatedSignature = generateSignature(data);
		System.out.println("generateSignature :" + generatedSignature);
		return generatedSignature.equals(incomingSignature);
	}

}
