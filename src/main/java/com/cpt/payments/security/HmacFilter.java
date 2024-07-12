package com.cpt.payments.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.exeption.ValidationException;
import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.service.HmacSHA256Service;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HmacFilter extends OncePerRequestFilter {

	private HmacSHA256Service hmacSHA256Service;

	public HmacFilter(HmacSHA256Service hmacSHA256Service) {
		this.hmacSHA256Service = hmacSHA256Service;
	}
	
	/**
	 * 1. validate hmacSHA256 logic - true/false
		2. IF IS_VALID,
			Create object of Authentication
			Place inside SecurityContextHolder
		3. IN_VALID
			throw exception & terminate the process.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("INVOKED HmacFilter**** hmacSHA256Service:" + hmacSHA256Service);


		WrappedRequest wrappedRequest = new WrappedRequest(request);

		String reqAsJson = wrappedRequest.getBody();
		
		/*
		 * StringBuilder requestBody = new StringBuilder(); BufferedReader reader =
		 * request.getReader(); String line; while ((line = reader.readLine()) != null)
		 * { requestBody.append(line); } reader.close();
		 * 
		 * String reqAsJson = requestBody.toString();
		 */
		
		System.out.println("from HTTPServletRequest"
				+ "||reqAsJson:" + reqAsJson);
		
		Gson gson = new Gson();
		reqAsJson = gson.toJson(
				gson.fromJson(reqAsJson, PaymentRequest.class));
		
		System.out.println("processed request from GSON "
				+ "||reqAsJson:" + reqAsJson);
		
		
		String signature = request.getHeader("signature");

		System.out.println("Values before signature generation"
				+ "|||reqAsJson:" + reqAsJson + "|signature:" + signature);
		
		boolean isSignatureValid = hmacSHA256Service.verifySignature(
				reqAsJson, signature);

		if(isSignatureValid) {
			System.out.println("Singature VALID, continue with further flow");


			//Put Authentication object in SecurityContextHolder. 
			//So that AuthorizationFilter can read and make decision.
			Authentication auth = new CustomAuthToken();			
			SecurityContextHolder.getContext().setAuthentication(auth);

			System.out.println("Authentication object in SecurityContextHolder");

			System.out.println("====> HmacFilter BEFORE calling next filter ");
			filterChain.doFilter(wrappedRequest, response); 
			System.out.println("====> HmacFilter AFTER calling next filter ");
			
			return;
		}

		System.out.println("INVALID SIGNATURE, cannot process request");

		throw new ValidationException(
				ErrorCodeEnum.SIGNATURE_INVALID_ERROR.getErrorCode(), 
				ErrorCodeEnum.SIGNATURE_INVALID_ERROR.getErrormessage(), 
				HttpStatus.BAD_REQUEST);
	}

}
