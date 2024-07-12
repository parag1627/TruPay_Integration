package com.cpt.payments.pojo;

public class PaymentRequest {

	private User user;
    private Payment payment;

    public PaymentRequest() {
    }

    public PaymentRequest(User user, Payment payment) {
        this.user = user;
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "user=" + user +
                ", payment=" + payment +
                '}';
    }
}
