package com.cpt.payments.service;

import com.cpt.payments.dto.PaymentRequestDto;

public interface Validator {
	
	public void doValidate(PaymentRequestDto request);

}
