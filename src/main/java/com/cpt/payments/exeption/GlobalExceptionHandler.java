package com.cpt.payments.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.pojo.PaymentErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<PaymentErrorResponse> handleValidationException(ValidationException ex) {
		System.out.println("Handling exception for ex:" + ex);

		PaymentErrorResponse errorResponse = new PaymentErrorResponse(
				ex.getErrorCode(), ex.getErrorMessage());

		System.out.println("returning error errorResponse:" + errorResponse);

		return new ResponseEntity<>(errorResponse, ex.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<PaymentErrorResponse> handleGenericException(Exception ex, WebRequest request) {
		System.out.println("Received unknown error ex:" + ex);

		PaymentErrorResponse errorResponse = new PaymentErrorResponse(
				ErrorCodeEnum.GENERIC_ERROR.getErrorCode(),
				ErrorCodeEnum.GENERIC_ERROR.getErrormessage()
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<PaymentErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
		System.out.println("Received unknown error ex:" + ex);

		PaymentErrorResponse errorResponse = new PaymentErrorResponse(
				ErrorCodeEnum.GENERIC_ERROR.getErrorCode(),
				ErrorCodeEnum.GENERIC_ERROR.getErrormessage()
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}


}

