package com.cpt.payments.dto;

public class PaymentDTO {
	  private String merchantTxnRef;
	    private String currency;
	    private String country;
	    private String locale;
	    private String shopperStatement;
	    private String successURL;
	    private String failureUrl;
	    private String amount;
	    private String paymentMethod;
	    private String paymentType;
	    private String providerId;

	    public PaymentDTO() {
	    }

	    

	    public String getMerchantTxnRef() {
			return merchantTxnRef;
		}



		public void setMerchantTxnRef(String merchantTxnRef) {
			this.merchantTxnRef = merchantTxnRef;
		}



		public String getCurrency() {
			return currency;
		}



		public void setCurrency(String currency) {
			this.currency = currency;
		}



		public String getCountry() {
			return country;
		}



		public void setCountry(String country) {
			this.country = country;
		}



		public String getLocale() {
			return locale;
		}



		public void setLocale(String locale) {
			this.locale = locale;
		}



		public String getShopperStatement() {
			return shopperStatement;
		}



		public void setShopperStatement(String shopperStatement) {
			this.shopperStatement = shopperStatement;
		}



		public String getSuccessURL() {
			return successURL;
		}



		public void setSuccessURL(String successURL) {
			this.successURL = successURL;
		}



		public String getFailureUrl() {
			return failureUrl;
		}



		public void setFailureUrl(String failureUrl) {
			this.failureUrl = failureUrl;
		}



		public String getAmount() {
			return amount;
		}



		public void setAmount(String amount) {
			this.amount = amount;
		}



		public String getPaymentMethod() {
			return paymentMethod;
		}



		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}



		public String getPaymentType() {
			return paymentType;
		}



		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}



		public String getProviderId() {
			return providerId;
		}



		public void setProviderId(String providerId) {
			this.providerId = providerId;
		}

		@Override
	    public String toString() {
	        return "PaymentDTO{" +
	                "merchantTxnRef='" + merchantTxnRef + '\'' +
	                ", currency='" + currency + '\'' +
	                ", country='" + country + '\'' +
	                ", locale='" + locale + '\'' +
	                ", shopperStatement='" + shopperStatement + '\'' +
	                ", successURL='" + successURL + '\'' +
	                ", failureUrl='" + failureUrl + '\'' +
	                ", amount=" + amount +
	                ", paymentMethod='" + paymentMethod + '\'' +
	                ", paymentType='" + paymentType + '\'' +
	                ", providerId='" + providerId + '\'' +
	                '}';
	    }
	}
