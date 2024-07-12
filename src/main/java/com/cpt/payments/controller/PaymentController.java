package com.cpt.payments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.dto.PaymentRequestDto;
import com.cpt.payments.dto.PaymentResponseDto;
import com.cpt.payments.exeption.ValidationException;
import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.pojo.PaymentResponse;
import com.cpt.payments.service.HmacSHA256Service;
import com.cpt.payments.service.PaymentService;
import com.google.gson.Gson;



@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	HmacSHA256Service hmacSHA256Service;

	@Autowired
	PaymentService paymentService;

	@PostMapping
	public ResponseEntity<PaymentResponse> initPayment
	(@RequestBody PaymentRequest paymentRequest) {
		System.out.println("initPayment() invoked|| paymentRequest:" + paymentRequest);
		
		System.out.println("modelMapper:" + modelMapper);

		PaymentRequestDto reqDTO = modelMapper.map(
				paymentRequest, PaymentRequestDto.class);

		PaymentResponseDto resDTO = paymentService.validateAndInitiate(reqDTO);

		PaymentResponse paymentResponse = modelMapper.map(
				resDTO, PaymentResponse.class);

		System.out.println("Returning response:" + paymentResponse);


		return new ResponseEntity<PaymentResponse>(
				paymentResponse, HttpStatus.CREATED);
	}
	

	@GetMapping("/msg")
	public ResponseEntity<String> getMessage() {
		System.out.println("getMessage invoked");
		return new ResponseEntity<>("Hello Team", HttpStatus.OK);
	}
}
