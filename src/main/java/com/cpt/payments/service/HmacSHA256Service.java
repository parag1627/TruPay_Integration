package com.cpt.payments.service;

public interface HmacSHA256Service {

	public String generateSignature(String data);

	public boolean verifySignature(String data, String incomingSignature);
}
