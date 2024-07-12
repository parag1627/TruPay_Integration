package com.cpt.payments.dto;

import com.cpt.payments.pojo.Payment;
import com.cpt.payments.pojo.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRequestDto {

	private UserDTO user;
    private PaymentDTO payment;
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public PaymentDTO getPayment() {
		return payment;
	}
	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "PaymentRequestDTO [user=" + user + ", payment=" + payment + "]";
	}
}
