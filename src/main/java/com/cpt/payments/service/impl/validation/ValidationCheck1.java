package com.cpt.payments.service.impl.validation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.dto.PaymentRequestDto;
import com.cpt.payments.exeption.ValidationException;
import com.cpt.payments.service.Validator;

@Service
public class ValidationCheck1 implements Validator {

	@Override
	public void doValidate(PaymentRequestDto request) {
		System.out.println("ValidationCheck1.doValidate invoked");

		if(request.getPayment().getProviderId() == null) {
			//throw exception

			ValidationException valEx = new ValidationException(
					ErrorCodeEnum.CHECK1_ERROR.getErrorCode(), 
					ErrorCodeEnum.CHECK1_ERROR.getErrormessage(), 
					HttpStatus.BAD_REQUEST);

			System.out.println("ValidationCheck1 validation failed. "
					+ "Throwing exception valEx:" + valEx);

			throw valEx;

		}

		System.out.println("ValidationCheck1 passed SUCCESSFUL");
	}

}
