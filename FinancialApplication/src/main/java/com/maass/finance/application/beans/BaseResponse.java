package com.maass.finance.application.beans;

public class BaseResponse {
	private int httpCode = 200;
	private String message = "Information retrieved successfully";
	private String bussinessFaultCode = "N/A";
	private String businessMessage = "N/A";
	
	public BaseResponse() {
		super();
	}
	
	public BaseResponse(int httpCode, String message, String bussinessFaultCode, String businessMessage) {
		super();
		
		setHttpCode(httpCode);
		setBussinessFaultCode(bussinessFaultCode);
		setBusinessMessage(businessMessage);
		setMessage(message);
	}
	
	public int getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBussinessFaultCode() {
		return bussinessFaultCode;
	}
	public void setBussinessFaultCode(String bussinessFaultCode) {
		this.bussinessFaultCode = bussinessFaultCode;
	}
	public String getBusinessMessage() {
		return businessMessage;
	}
	public void setBusinessMessage(String businessMessage) {
		this.businessMessage = businessMessage;
	}
}
