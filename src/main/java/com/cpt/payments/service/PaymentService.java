package com.cpt.payments.service;

import com.cpt.payments.dto.PaymentRequestDto;
import com.cpt.payments.dto.PaymentResponseDto;

public interface PaymentService {
	public PaymentResponseDto validateAndInitiate(PaymentRequestDto request);
}
