package com.cpt.payments.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cpt.payments.constants.ValidationRules;
import com.cpt.payments.dto.PaymentRequestDto;
import com.cpt.payments.dto.PaymentResponseDto;
import com.cpt.payments.service.PaymentService;
import com.cpt.payments.service.Validator;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${payment.validators}")
	private String validatorRules;

	@Autowired
	private ApplicationContext appContext;

	@Override
	public PaymentResponseDto validateAndInitiate(PaymentRequestDto request) {
		System.out.println("PaymentServiceImpl received request:" + validatorRules);

		String[] validationRules = validatorRules.split(",");

		for (String validationRule : validationRules) {

			ValidationRules validationEnum = ValidationRules.fromName(validationRule);
			System.out.println("validationEnum " + validationEnum);

			Validator validationBean = (Validator) appContext.getBean(validationEnum.getClazz());

			System.out.println("Got this bean from AppContext validationBean:" + validationBean);
			boolean isValid = validationBean.doValidate(request);

		}
		
		
		PaymentResponseDto response = new PaymentResponseDto();
		response.setRedirectUrl("https://ai.google.dev/aistudio");
		return response;
	}

}
