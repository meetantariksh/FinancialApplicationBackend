package com.maass.finance.application.beans.exceptions;

public class ApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = null;
	private String bussinessFaultCode = null;
	private String businessMessage = null;
	
	public ApplicationException(String exceptionMessage, String message, String businessFaultCode, String businessMessage) {
		
		super(exceptionMessage);
		
		setBusinessMessage(businessMessage);
		setBussinessFaultCode(businessFaultCode);
		setMessage(message);
	}
	
	public String getMessage() {
		return message;
	}
	private void setMessage(String message) {
		this.message = message;
	}
	public String getBussinessFaultCode() {
		return bussinessFaultCode;
	}
	private void setBussinessFaultCode(String bussinessFaultCode) {
		this.bussinessFaultCode = bussinessFaultCode;
	}
	public String getBusinessMessage() {
		return businessMessage;
	}
	private void setBusinessMessage(String businessMessage) {
		this.businessMessage = businessMessage;
	} 

}
