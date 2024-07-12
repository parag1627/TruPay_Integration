package com.cpt.payments.service.impl;

import org.junit.jupiter.api.Test;

import com.cpt.payments.pojo.Payment;
import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.pojo.User;
import com.google.gson.Gson;

public class HmacSHA256ServiceImplTest {
	HmacSHA256ServiceImpl service = new HmacSHA256ServiceImpl();

	@Test
	public void testMethod() {
		System.out.println("Running testMethod");

		// Creating User object and setting its fields
		User user = new User();
		user.setEndUserId("123456");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setEmail("john.doe@example.com");
		user.setPhone("+1234567890");

		// Creating Payment object and setting its fields
		Payment payment = new Payment();
		payment.setMerchantTxnRef("txn123456");
		payment.setCurrency("USD");
		payment.setCountry("US");
		payment.setLocale("en_US");
		payment.setShopperStatement("Purchase at Example Shop");
		payment.setSuccessURL("https://example.com/success");
		payment.setFailureUrl("https://example.com/failure");
		payment.setAmount("100.00");
		payment.setPaymentMethod("CreditCard");
		payment.setPaymentType("Onlinei");
		payment.setProviderId("TrustlY");

		// Creating PaymentRequest object and setting its User and Payment objects
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setUser(user);
		paymentRequest.setPayment(payment);

		// convert this paymentRequest into JSON String
		Gson gson = new Gson();
		String jsonData = gson.toJson(paymentRequest);

		String signature = service.generateSignature(jsonData);

		System.out.println("signature:" + signature);

	}
}
