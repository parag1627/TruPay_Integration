package com.cpt.payments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.dto.PaymentRequestDto;
import com.cpt.payments.dto.PaymentResponseDto;
import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.pojo.PaymentResponse;
import com.cpt.payments.service.PaymentService;



@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<PaymentResponse> initPayment
	(@RequestBody PaymentRequest paymentRequest) {
		System.out.println("modelMapper "+modelMapper);
		
	PaymentRequestDto reqDto = modelMapper.map(paymentRequest, PaymentRequestDto.class);
	
	PaymentResponseDto responseDto = paymentService.validateAndInitiate(reqDto);
	
	PaymentResponse paymentResponse= modelMapper.map(responseDto, PaymentResponse.class);
	
	
		System.out.println("RESPONSE: "+ paymentResponse );
		
		return new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.CREATED);
		
	}
}
