package com.cpt.payments.constants;

public enum ErrorCodeEnum {
	GENERIC_ERROR(10000, "Unable to process your request, please try again later."),
	CHECK1_ERROR(10001, "Invalid Request, please provider valid phoneno"),
	
	SIGNATURE_GENERATION_ERROR(10002, "Failure generating Signature"),
	SIGNATURE_INVALID_ERROR(10002, "Invalid Signature, Cannot process request");
	
	private final int errorCode;
    private final String errormessage;
    
    ErrorCodeEnum(int errorCode, String errormessage) {
    	this.errorCode = errorCode;
    	this.errormessage = errormessage;
    }
    
    public int getErrorCode() {
		return errorCode;
	}
    
    public String getErrormessage() {
		return errormessage;
	}

}
