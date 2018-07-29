package com.maass.finance.application.beans.authentication;

public class SeccondFactorAuthenticationDetails {
	private String email;
	private String securityQuestionID;
	private String securityQuestionText;
	private boolean isEmailBlocked = false;
	private boolean isSecurityQuestionsBlocked = false;
	
	public SeccondFactorAuthenticationDetails(String email,
			String securityQuestionID, String securityQuestionText, boolean isEmailBlocked, boolean isSecurityQuestionsBlocked) {
		super();
		this.email = email;
		this.securityQuestionID = securityQuestionID;
		this.securityQuestionText = securityQuestionText;
		this.isEmailBlocked = isEmailBlocked;
		this.isSecurityQuestionsBlocked = isSecurityQuestionsBlocked;
	}
	public boolean isEmailBlocked() {
		return isEmailBlocked;
	}
	public boolean isSecurityQuestionsBlocked() {
		return isSecurityQuestionsBlocked;
	}
	public SeccondFactorAuthenticationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setEmailBlocked(boolean isEmailBlocked) {
		this.isEmailBlocked = isEmailBlocked;
	}
	public void setSecurityQuestionsBlocked(boolean isSecurityQuestionsBlocked) {
		this.isSecurityQuestionsBlocked = isSecurityQuestionsBlocked;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecurityQuestionID() {
		return securityQuestionID;
	}
	public void setSecurityQuestionID(String securityQuestionID) {
		this.securityQuestionID = securityQuestionID;
	}
	public String getSecurityQuestionText() {
		return securityQuestionText;
	}
	public void setSecurityQuestionText(String securityQuestionText) {
		this.securityQuestionText = securityQuestionText;
	}
	
}
