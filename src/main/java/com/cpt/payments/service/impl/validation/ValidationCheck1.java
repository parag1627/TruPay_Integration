package com.cpt.payments.service.impl.validation;

import org.springframework.stereotype.Service;

import com.cpt.payments.dto.PaymentRequestDto;
import com.cpt.payments.service.Validator;

@Service
public class ValidationCheck1 implements Validator {

	@Override
	public boolean doValidate(PaymentRequestDto request) {
		System.out.println("ValidationCheck1.doValidate invoked");
		
		return false;
	}

}
